(function($, document, window, viewport){

	viewport.use('bootstrap4');

	function setMenuWidth(){
		$(".nav-nd").css("margin-left", $(".navbar-brand-container").offset().left - 10);
	}

	function loadAllMedia(){
		if ($( $(".media-once").length)){
			$(".media-once").removeClass("media-once");
			$(".show-more").hide();
		}
	}

	function getNewsbyPill(c) {
		if (c != null){
			$(".media-cnt .col-20 > div").each(function(){
				var el = $(this);
				if (c != el.data("cat")){
					el.addClass("bkg-inactive");
				} else {
					el.removeClass("bkg-inactive");
				}
			})
		} else {
			$(".media-cnt .bkg-inactive").removeClass("bkg-inactive");
		}
	}

	function scrollToEl(el){
		var k = $("#header").height() + (el[0].id == "hp2" ? ($("#hp2 .nav-tabs").height() + 1) : 0);
		$('body').animate({
			scrollTop: el.offset().top - k,
		}, {
			duration: "slow",
			easing: "easeInOutCubic",
			complete: function(){
				removeStopBinding();
			}
		});
	}

	function equalHeight(pParent, pChildren,pByRow, pProperty){

		var ehByRow = pByRow || false;
		var ehProperty = pProperty || "height";

		$(pParent).each(function() {
			$(this).children(pChildren).matchHeight({
			    byRow: ehByRow,
			    property: ehProperty,
			});
		});
	}

	var mySection = null, elPrev = null, elNext = null;
	var lastScrollTop = 0, st = 0, direction = "";

	var detectDirection = function(){
		st = window.pageYOffset;
		direction = st > lastScrollTop ? "down" : "up";
		lastScrollTop = st;
		return direction;
	}

	var elFx = null,
		elFxOuter = null,
		elFxHeight = null,
		headerContainerHeight = $(".header-container").height(),
		mainContainerPaddingTop = $(".main-container").length ? (Number($(".main-container").css("padding-top").replace("px", ""))) : 0,
		offsetTop = 0,
		elFxFromBottom = null;

	function topFx(){

		var offsetBottom = $('#footer').offset().top  - elFxFromBottom;
		var documentScrollTop = $(document).scrollTop();

		if (documentScrollTop >= offsetTop && documentScrollTop <= offsetBottom) {
			if(!elFx.hasClass('affix')){
				elFx.addClass('affix');
			}
		} else if(elFx.hasClass('affix')){
			elFx.removeClass('affix');
		}

		var dir = detectDirection();
		if(dir == 'down' && !elFx.hasClass('slideUp') && $(document).scrollTop() >= offsetTop * 1.3){
			elFx.removeClass('slideDown').removeClass('anEnd').addClass('slideUp');
		} else if (dir == 'up' && elFx.hasClass('slideUp')){
			elFx.removeClass('slideUp').removeClass('anEnd').addClass('slideDown');
		} else if ($(document).scrollTop() < offsetTop * 1.3 && $(document).scrollTop() > offsetBottom){
			elFx.removeClass('slideUp').removeClass('slideDown').removeClass('anEnd')
		}
	}

	function initAsideScrollController(pEl){
		equalHeight(".main-container", ".col-to-match", true, "min-height");
		elFx = $(pEl);
		elFxOuter = elFx.closest('.outer-affix');
		elFxHeight = elFx.height() + Number(elFx.css("padding-top").replace("px", "")) + Number(elFx.css("padding-bottom").replace("px", ""));
		elFxFromBottom = headerContainerHeight + mainContainerPaddingTop + elFxHeight + Number($("#footer").css("margin-top").replace("px", ""));
	}

	function showHistory(pSide){
		if (pSide == "sx"){

			$("#col-sx").prepend($(".slick-history-cnt").detach());

			$("#col-dx .to-dx").hide();
			$(".to-dx").show();

		} else if (pSide == "dx"){

			$("#col-dx").prepend($(".slick-history-cnt").detach());

			$(".to-dx").hide();
			$("#col-dx .to-dx").show();

		}

	}


	$(function(){

		var currentViewport = "", previousViewport = "";

		setMenuWidth();

		$('#menu').on('shown.bs.collapse', function () {
			$('body').addClass('fixed-body');
		});
		$('#menu').on('hidden.bs.collapse', function () {
			$('body').removeClass('fixed-body');
		});
		$("#menu .nav-btn").click(function(){
			var nd = $(this).data("target");
			$(nd).addClass('animation-menu-in');
			$('body').addClass('fixed-nav-st');

		});
		$("#menu .heading").click(function(e){
			if (viewport.is("<=lg")) {
				//e.preventDefault();
				var nd = $(this).closest('.nav-nd');
				$(nd).removeClass('animation-menu-in');
				$('body').removeClass('fixed-nav-st');
			}
		})

		$("#menu .lng-cnt span").click(function(){
			$(this).toggleClass("active");
		});

		$(".header-tools .btn-search").click(function () {
			var s = $(this).closest(".search-cnt");
			var i = s.find("input");
			s.toggleClass("closed");
			if (!s.hasClass("closed")){ i.focus(); } else { i.blur(); }
		});

		$(".header-tools form").submit(function() {
			console.log($(".header-q").val())
			q = $.trim( $(".header-q").val() );
			console.log(q.length)
			if ( q.length <= 3 ) {
				return false;
			}
		});

		if ($("body.hp").length){

			var hpSlick = $('#hp1 .lead.slick');
			$("#hp1").css("background-image", ("url(" + $("#hp1 .lead > .slide").data("src") + ")") );

			hpSlick.slick({
				arrows: false,
				dots: true
			});

			hpSlick.on('afterChange', function(event, slick, currentSlide, nextSlide){
				$("#hp1").css("background-image", ("url(" + $(".slick-current").attr("data-src") + ")") );
			});

			$(".hp2-tab-cnt .nav-link").click(function(){
				scrollToEl($("#hp2"));
			});

			$(".show-more").click(function(){
				loadAllMedia();
			});


			$(".nav-pills .nav-link").click(function(){

				loadAllMedia();

				var el = $(this), c = null;
				var np = el.closest(".nav-pills");
				if (!el.hasClass("active")) {
					np.find(".nav-link").removeClass("active");
					el.addClass("active");
					c = el.data("cat");
				} else {
					el.removeClass("active");
				}
				getNewsbyPill(c);

			});

			equalHeight("#hp2news", ".col-to-match");

		} else if ($("body.in").length) {

			var i = 0;
			$(".breadcrumb-item:nth-child(n+2)").each(function(){
				var id = $(this).data("id");
				$("#" + id).addClass("active");
				$(".nav-aside ." + id).addClass("active");
				if (i == 0){
					$("#" + id +"-nd").addClass("animation-menu-in");
				}
			});

			$(".card-toggler").click(function(){
				var c = $(this).closest(".card");
				if (c.hasClass("open")){
					c.removeClass("open");
				} else {
					$(".card.open").removeClass("open");
					c.addClass("open");
				}
			})

			if ( $(".nav-aside").length ){

				initAsideScrollController(".nav-aside");

				$(".accordion .collapse").on('show.bs.collapse shown.bs.collapse hide.bs.collapse hidden.bs.collapse', function () {
					equalHeight(".main-container", ".col-to-match", true, "min-height");
					topFx();
				});

				$(window).scroll(function(){
					topFx();
				});

				$(window).resize(function() {
					topFx();
				});

			}

			if ( $(".history-cnt").length ){

				var historySlick = $(".slick-history");
				historySlick.slick({
					arrows: false,
					dots: true,
					adaptiveHeight: true
				});

				if (viewport.is(">=lg")){
					showHistory("dx");
				}

			}

			$(".toggler").click(function(){
				$(this).closest(".toggleable").toggleClass("open");
				if ( $(".history-cnt").length ){
					historySlick.slick("slickGoTo", historySlick.slick("slickCurrentSlide") );
				}
			});

			if ( $(".form-search-cnt.form-extended ").length ){
				if (viewport.is(">=lg")){
					equalHeight(".row", ".col-to-match-lg", true, "min-height");
				}
			}

			$(window).resize(
				viewport.changed(function() {
					currentViewport = viewport.current();
					if (currentViewport != previousViewport){

						if ( $(".nav-aside").length ){
							initAsideScrollController(".nav-aside");
						}

						if ( $(".table-docs").length ){
							var t =  $(".table-docs");
							if (viewport.is("<=sm")){
								t.find("th").each(function(){
									$(this).html($(this).html().replace(/\s/g, "<br />"));
								})
							} else {
								t.find("th").each(function(){
									$(this).html($(this).html().replace(/<br\s*\/?>/g, " "));
								})
							}
						}

						if ( $(".history-cnt").length ){
							if (viewport.is(">=lg")){
								showHistory("dx");
							} else {
								showHistory("sx");
							}
						}

						if ( $(".form-search-cnt.form-extended ").length ){
							if (viewport.is(">=lg")){
								equalHeight(".row", ".col-to-match-lg", true, "min-height");
							} else {
								$(".col-to-match-lg").removeAttr("style");
							}
						}

					}
					previousViewport = currentViewport;
				}, 150)
			);

		}

		$(window).resize(function() {
			setMenuWidth();
		});

	});

})(jQuery, document, window, ResponsiveBootstrapToolkit);