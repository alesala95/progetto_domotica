#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>
#include <ESP8266WiFi.h>        
#include <ESP8266WiFiMulti.h>  
#include <ESP8266mDNS.h>        
#include <ESP8266WebServer.h>

ESP8266WiFiMulti wifiMulti; 
ESP8266WebServer server(80);

String codiceFrigo = "sf0001ma";
int alimentiResId[]={1,2,3,4,5,6,7,8,9,10,11,12};

int pinAggiungi = 13;
int pinRimuovi = 12;

boolean buttonAggiungi;
boolean buttonRimuovi;

void setup() {

  buttonAggiungi = false;
  buttonRimuovi = false;
  
  pinMode(pinAggiungi,INPUT);
  pinMode(pinRimuovi,INPUT);
  
  Serial.begin(115200);         
  delay(10);
  Serial.println('\n');

  wifiMulti.addAP("Solaris", "84203733a784203733a7842037");  
  wifiMulti.addAP("meme", "pepefrog");
  //wifiMulti.addAP("WmaltaNew","rete1privata2malta3family4");

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
  
}

void aggiungiAlimentoRandom (){

    String baseUrl = "http://smartfridgeifts.altervista.org/smartfridge2/aggiungiAlimento.php/";
    
    String endpoint = "?";
    
    endpoint += "idAlimento=";
    endpoint += random (1,13);

    endpoint += "&";

    endpoint += "codiceFrigo=";
    endpoint += codiceFrigo;

    endpoint += "&";

    endpoint += "data_inserimento=";
    endpoint += "2018-03-23";

    String request = baseUrl += endpoint;

    HTTPClient http;  
    http.begin(request);
    http.GET();
    //http.end;

    Serial.println("Alimento aggiunto");
}

void rimuoviAlimentoRandom (){

    String baseUrl = "http://smartfridgeifts.altervista.org/smartfridge2/rimuoviAlimento.php/";
    
    String endpoint = "?";
    
    endpoint += "idAlimento=";
    endpoint += random (1,13);

    endpoint += "&";

    endpoint += "codiceFrigo=";
    endpoint += codiceFrigo;

    String request = baseUrl += endpoint;

    HTTPClient http;  
    http.begin(request); 
    http.GET();
    //http.end;

    Serial.println("Alimento rimosso");
}

void pingResp() { //Handler
  
  //message += server.argName(i) + ": ";     //Get the name of the parameter
  //message += server.arg(i) + "\n";              //Get the value of the parameter
  server.send(200, "text/plain", "");       //Response to the HTTP request
  Serial.println("Richiesta di ping ricevuta");
}

void inviaCodice(){

  StaticJsonBuffer<200> JSONbuffer;
  JsonObject& JSONencoder = JSONbuffer.createObject();
 
  JSONencoder["codiceFrigo"] = codiceFrigo;
  
  /*JsonArray& values = JSONencoder.createNestedArray("colors");
  values.add(r);
  values.add(g);
  values.add(b);
 
  Serial.println("\nPretty JSON message from buffer: ");*/
  char JSONmessageBuffer[300];
  JSONencoder.prettyPrintTo(JSONmessageBuffer, sizeof(JSONmessageBuffer));
  Serial.println(JSONmessageBuffer);

  server.send(200, "text/plain", JSONmessageBuffer);
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
