#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>
#include <ESP8266WiFi.h>        
#include <ESP8266WiFiMulti.h>  
#include <ESP8266mDNS.h>        
#include <ESP8266WebServer.h>

ESP8266WiFiMulti wifiMulti; 
ESP8266WebServer server(80);

String codiceFrigo = "sf0001ma";
String pw = "smart.alpaca123";

int alimentiResId[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};

int pinAggiungi = D7;
int pinRimuovi = D6;

int pinLampadina = D5;
int pinAllarme = D8;
int pinVM = D0;

boolean buttonAggiungi;
boolean buttonRimuovi;

int stato_frigo = 1;
int stato_freezer = 1;

double temperatura_frigo = 4.0;
double temperatura_freezer = -3.0;

int val_Adc = 0;
float temp = 0;

void setup() {

  buttonAggiungi = false;
  buttonRimuovi = false;
  
  pinMode(pinAggiungi,INPUT);
  pinMode(pinRimuovi,INPUT);

  pinMode(pinLampadina,OUTPUT);
  pinMode(pinAllarme,OUTPUT);
  pinMode(pinVM,OUTPUT);
  
  Serial.begin(115200);         
  delay(10);
  Serial.println('\n');

  wifiMulti.addAP("Solaris", "84203733a784203733a7842037");  
  wifiMulti.addAP("meme", "pepefrog");

  Serial.println("Connecting ...");
  int i = 0;
  while (wifiMulti.run() != WL_CONNECTED) { // si connette alla stazione più potente tra quelle sopra
    delay(1000);
    Serial.print(++i); Serial.print(' ');
  }
  Serial.println('\n');
  Serial.print("Connected to ");
  Serial.println(WiFi.SSID());              // Tell us what network we're connected to
  Serial.print("IP address:\t");
  Serial.println(WiFi.localIP());           // Send the IP address of the ESP8266 to the computer

  if (!MDNS.begin("SmartFridgeMA",WiFi.localIP())) {             // Start the mDNS responder for esp8266.local
    Serial.println("Error setting up MDNS responder!");
  }
  Serial.println("mDNS responder started");
  MDNS.addService("http","tcp",80);

  server.on("/", pingResp);
  server.on("/ottieniCodice", inviaCodice); 
  
  server.begin();
  Serial.println("HTTP server started");
}

void loop() { 

  aggiornaStato();

  server.handleClient();   

  if ((digitalRead(pinAggiungi)==HIGH) and (buttonAggiungi == false)){
      
      aggiungiAlimentoRandom();
      buttonAggiungi = true;
  }
  
  if ((digitalRead(pinRimuovi)==HIGH) and (buttonRimuovi == false)){

      rimuoviAlimentoRandom();
      buttonRimuovi = true;
  }

  if ((digitalRead(pinAggiungi)==LOW) and (buttonAggiungi == true)){

      buttonAggiungi = false;
  }

  if ((digitalRead(pinRimuovi)==LOW) and (buttonRimuovi == true)){

      buttonRimuovi = false;
  }

  recuperaStato();
  
  val_Adc = analogRead(0);
  temp = ((val_Adc * 0.00488) - 0.5) / 0.01;
  
  
  
  delay (5000);
  
}

void aggiungiAlimentoRandom (){

    String baseUrl = "http://smartfridgeifts.altervista.org/smartfridge2/aggiungiAlimentiDumb.php/";
    
    String endpoint = "?";
    
    /*endpoint += "idAlimento=";
    endpoint += random (1,13);

    endpoint += "&";*/

    endpoint += "codiceFrigo=";
    endpoint += codiceFrigo;

    String request = baseUrl += endpoint;

    HTTPClient http;  
    http.begin(request);
    int code = http.GET();

    if(code > 0){

      String payload = http.getString();
    }
    
    http.end();

    Serial.println("Alimento aggiunto");
}

void rimuoviAlimentoRandom (){

    String baseUrl = "http://smartfridgeifts.altervista.org/smartfridge2/rimuoviAlimentiDumb.php/";
    
    String endpoint = "?";
    
    /*endpoint += "idAlimento=";
    endpoint += random (1,13);

    endpoint += "&";*/

    endpoint += "codiceFrigo=";
    endpoint += codiceFrigo;

    String request = baseUrl += endpoint;

    HTTPClient http;  
    http.begin(request); 
    int code = http.GET();

    if (code > 0){

      String payload = http.getString();   
    }
    
    http.end();

    Serial.println("Alimento rimosso");
}

void pingResp() { //Handler
  
  //message += server.argName(i) + ": ";     //Get the name of the parameter
  //message += server.arg(i) + "\n";              //Get the value of the parameter
  server.send(200, "text/plain", "");       //Response to the HTTP request
  Serial.println("Richiesta di ping ricevuta");
}

void inviaCodice(){

  String codiceP = server.arg("pw");

  StaticJsonBuffer<200> JSONbuffer;
  JsonObject& JSONencoder = JSONbuffer.createObject();

  if (codiceP.equals(pw)){

     JSONencoder["success"] = 1;
     JSONencoder["codiceFrigo"] = codiceFrigo;
     
     char JSONmessageBuffer[300];
     JSONencoder.prettyPrintTo(JSONmessageBuffer, sizeof(JSONmessageBuffer));
     Serial.println(JSONmessageBuffer);

     server.send(200, "text/plain", JSONmessageBuffer);
 }else{

    JSONencoder["success"] = 0;
    JSONencoder["codiceFrigo"] = "";
     
    char JSONmessageBuffer[300];
    JSONencoder.prettyPrintTo(JSONmessageBuffer, sizeof(JSONmessageBuffer));
    Serial.println(JSONmessageBuffer);

    server.send(200, "text/plain", JSONmessageBuffer);
 }
}

void recuperaStato (){

    String baseUrl = "http://smartfridgeifts.altervista.org/smartfridge2/recuperaStatoFrigo.php/";
    
    String endpoint = "?";
    
    endpoint += "codiceFrigo=";
    endpoint += codiceFrigo;
    
    String request = baseUrl += endpoint;

    HTTPClient http;  
    http.begin(request); 
    int code = http.GET();
    String payload;

    if (code > 0) {
 
      payload = http.getString();   
      Serial.println(payload);                     
    }
    
    http.end();

    outputLuci(payload.substring(8,9).toInt(),payload.substring(20,21).toInt(),payload.substring(27,28).toInt());
}

int outputLuci (int luci, int allarme, int vm){

  Serial.println("Valori: ");
  Serial.println(luci);
  Serial.println(allarme);
  Serial.println(vm);

  digitalWrite (pinLampadina, luci);
  digitalWrite (pinAllarme, allarme);
  digitalWrite (pinVM,vm);
}

void aggiornaStato(){

    String baseUrl = "http://smartfridgeifts.altervista.org/smartfridge2/aggiornaStatoF.php/";
    
    String endpoint = "?";

    endpoint += "codiceFrigo=";
    endpoint += codiceFrigo;

    endpoint += "&";
    
    endpoint += "stato_frigo=";
    endpoint += stato_frigo;

    endpoint += "&";
    
    endpoint += "stato_freezer=";
    endpoint += stato_freezer;

    endpoint += "&";
    
    endpoint += "temperatura_frigo=";
    endpoint += temperatura_frigo;

    endpoint += "&";
    
    endpoint += "temperatura_freezer=";
    endpoint += temperatura_freezer;

    String request = baseUrl += endpoint;

    HTTPClient http;  
    http.begin(request); 
    int code = http.GET();
    
    if (code > 0) {
 
      String payload = http.getString();   
      Serial.println(payload);                     
    }

    http.end();
    
    Serial.println("Stato aggiornato");  
}


/*
int leggiStatoDue (){
  

  int index = 0;
  int accesa = 0;

  String url = "http://edylc.altervista.org/seleziona.php?";
  url+= "id=" + String(idLampada);
  
  if(!client.connect(server,port)){

     Serial.println("Errore di connessione");
     return -1;
  }

  client.print(String("GET ") + url + " HTTP/1.1\r\n" +
               "Host: 217.182.10.130" + "\r\n\r\n");
               //"Host: " + host + "\r\n" +  
               //"Connection: close\r\n\r\n");

               

  int timeout = millis() + 5000;
   
   while (client.available() == 0) {
        
     if (timeout - millis() < 0) {
        
        Serial.println(">>> Client Timeout !");
        client.stop();
        return -1;
      } 
   }

   Serial.println("Inizio trasmissione");
   int inizio = millis();
   while (client.available()) {
      
        String linea = client.readStringUntil('\r');
   
        response[index] = linea;
        index++;
        
        if(index==12) break;
   }
   Serial.println("Fine trasmissione");
   Serial.println(millis()-inizio);
   
   if(response[9].charAt(0)=='1'){

      return 1;
   }

   return accesa;
}
*/
