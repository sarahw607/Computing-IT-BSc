<!--
function IsEmail(email) {
  var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return regex.test(email);
}

function setCookie(name, value, exdays) {
  var today = new Date();
	var h = location.host;
  expires = new Date();
  expires.setTime(today.getTime() + exdays);
  document.cookie = name + "=" + value + ";path=/;expires=" + expires.toGMTString() + ";domain=" + h.substring(h.indexOf('.'),h.length);
}


function getCookie(Name){ 
  var search = Name + "=" 
  if(document.cookie.length > 0){
    offset = document.cookie.indexOf(search);
    if(offset != -1){
      offset += search.length;
      end = document.cookie.indexOf(";", offset);
      if(end == -1){ end = document.cookie.length; }
      return unescape(document.cookie.substring(offset, end)) ;
    }
  } 
}

function _setBrowser(){
	var userAgent = navigator.userAgent.toLowerCase();
	// Figure out what browser is being used
	$.browser = {
		version: (userAgent.match( /.+(?:rv|it|ra|ie|me|ve)[\/: ]([\d.]+)/ ) || [])[1],
		chrome: /chrome/.test( userAgent ),
		safari: /webkit/.test( userAgent ) && !/chrome/.test( userAgent ),
		opera: /opera/.test( userAgent ),
		firefox: /firefox/.test( userAgent ),
		msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
		msie10: /msie 10/.test( userAgent ) && !/opera/.test( userAgent ),
		msie9: /msie 9/.test( userAgent ) && !/opera/.test( userAgent ),
		msie8: /msie 8/.test( userAgent ) && !/opera/.test( userAgent ),
		msie7: /msie 7/.test( userAgent ) && !/opera/.test( userAgent ),
		msie6: /msie 6/.test( userAgent ) && !/opera/.test( userAgent ),
		mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent ),
		gecko: /[^like]{4} gecko/.test( userAgent ),
		presto: /presto/.test( userAgent ),
		xoom: /xoom/.test( userAgent ),
		android: /android/.test( userAgent ),
		androidVersion: (userAgent.match( /.+(?:android)[\/: ]([\d.]+)/ ) || [0,0])[1],
		iphone: /iphone|ipod/.test( userAgent ),
		iphoneVersion: (userAgent.match( /.+(?:iphone\ os)[\/: ]([\d_]+)/ ) || [0,0])[1].toString().split('_').join('.'),
		ipad: /ipad/.test( userAgent ),
		ipadVersion: (userAgent.match( /.+(?:cpu\ os)[\/: ]([\d_]+)/ ) || [0,0])[1].toString().split('_').join('.'),
		blackberry: /blackberry/.test( userAgent ),
		winMobile: /Windows\ Phone/.test( userAgent ),
		winMobileVersion: (userAgent.match( /.+(?:windows\ phone\ os)[\/: ]([\d_]+)/ ) || [0,0])[1]
	};
	$.browser.mobile = ($.browser.iphone || $.browser.android || $.browser.blackberry || $.browser.winMobile || $.browser.winMobileVersion );
	$.browser.webkit = ($.browser.chrome || $.browser.safari);
};

function resizeHeader(){
	if($.browser.mobile){
		var w = $('header').width();
		$('header').css({'height': '85px'});
		$('header .logo').css({'width': '153px','height': '80px','margin':'11px 0 0 5px'});
		$('#animvideo').css({'width': Math.round(w)+'px','height':Math.round((360/944)*w)+'px'})
	} else {
		$('header').css({'height':'125px'});
		$('header .logo').css({'width':'275px','height':'144px'});
	}
}

function launchJoin(){
	$('#joinbutton').trigger('click');
}

$(document).ready(function(){
	_setBrowser();
	resizeHeader();

/*
	var cmsg = getCookie('signups_cookie_login');
	if(cmsg != null && cmsg != ""){
		if( ($('ul.login').find('a.logout').length <= 0) && ($('ul.login').find('a.login').attr('rel') == "") ){
			$.ajax({
				url: "/process/auto-login.php",
				cache: false,
				type: "POST",
				data: "&login_id=" + cmsg,
				success: function(data){
					location.reload();
				}
			});
		}
	}
*/

	$('.external').click(function(){ window.open($(this).attr('href')); return false; });

	$('#content img').each(function(){ $(this).attr('style','') });
	$('header .logo').click(function(){ location.href = "/"; });
	$('header nav p').click(function(){ location.href = "#footer"; });
	$('footer nav ul').last().css({'border':'0','margin':'0'});

	$('aside.offersnav li a span').mouseenter(function(){
		$(this).css({'background-image':'url(' + $(this).attr('data-src') + ')'});
	}).mouseleave(function(){
		if(!$(this).parent('a').hasClass('sel')){
			$(this).css({'background-image':'none'});
		}
	});

	$('aside.offersnav li a').each(function(){
		if($(this).hasClass('sel')){
			$(this).children('span').css({'background-image':'url(' + $(this).children('span').attr('data-src') + ')'})
		}
	})

	if(!$.browser.mobile){
		$('#offers .offerlist li').each(function(index){
			var i = (index+1);
			if((i%2) === 1){ $(this).addClass('clear'); } else { $(this).css({'float':'right'}) }
		})
	}

	if(document.getElementById('carousel')){
		$('#carousel').flexslider({
			animation: "slide",
			animationLoop: true,
			controlNav: false,
			directionNav: true,
			pauseOnHover: true,
			namespace: "carousel-"
		})
	}

	$('#pagepoll').submit(function(){
		$.ajax({
			url: "/process/poll-submit.php",
			cache: false,
			type: "POST",
			data: "&data_poll_id=" + $('#data_poll_id').val() + "&data_answer=" + $('#pagepoll input[type=radio]:checked').val(),
			success: function(data){
				$("#pagepoll").html(data);
			},
			error: function(data){
				$("#pagepoll .error").html('Sorry there was a problem saving your vote, please try again').slideDown().delay('3000').slideUp();
			}
		});
	})

	$('#typequestion').submit(function(){
		if($('#data_answer').val().length > 0){
			$.ajax({
				url: "/process/type-question-submit.php",
				cache: false,
				type: "POST",
				data: "&data_type_id=" + $('#data_type_id').val() + "&data_answer=" + $('#data_answer').val(),
				success: function(data){
					$("#typequestion").html(data);
				},
				error: function(data){
					$("#typequestion .error").html('Sorry there was a problem saving your answer, please try again').slideDown().delay('3000').slideUp();
				}
			});
		} else {
			$("#typequestion .error").html('Please enter an answer').slideDown().delay('3000').slideUp();
		}
	})

	$('.confirm').click(function(){
		var t = $(this);
		var p = $(this).parent('p').parent('form');
		var ml = $(this).parent('p');
		var li = p.parent('div').parent('li');

		if(p.find('input[type=text]').length > 0 || p.find('select').length > 0){
			var e = ""; var str = "";
			var f = p.find('input[type=text]');
			if(f.length > 0){
				f.each(function(){
					if($(this).val() !== ""){
						str += "&" + $(this).attr('name') + "=" + $(this).val();
					} else {
						e = "X";
					}
				})
			}
			var s = p.find('select');
			if(s.length > 0){
				s.each(function(){
					if($(this).val() !== ""){
						str += "&" + $(this).attr('name') + "=" + $(this).val();
					} else {
						e = "X";
					}
				})
			}
			if(e == ""){
				li.addClass('accepted');
				li.find('.loading').fadeIn();
				t.fadeOut();
				$.ajax({
					url: "/process/offer-confirm-submit.php",
					cache: false,
					type: "POST",
					data: "&data_offer_id=" + p.attr('id').substring(5) + str,
					success: function(data){
						//p.html(data);
						//li.find('.sharethis').show();
						//li.find('.loading').hide();
						//li.addClass('accepted');
						location.href = location.href + "#offer" + data;
					},
					error: function(data){
						li.find('.loading').hide();
						t.fadeIn();
						li.removeClass('accepted');
						p.find('.error').html('Sorry there was a problem confirming your offer, please try again').slideDown().delay('3000').slideUp();
					}
				});
			} else {
				p.find('.field').slideDown();
				p.find('.error').html('Please make sure you answer the required questions').slideDown().delay('3000').slideUp();
			}
		} else {
			li.addClass('accepted');
			li.find('.loading').fadeIn();
			t.fadeOut();
			$.ajax({
				url: "/process/offer-confirm-submit.php",
				cache: false,
				type: "POST",
				data: "&data_offer_id=" + p.attr('id').substring(5),
				success: function(data){
					//p.html(data);
					//li.find('.loading').hide();
					//li.find('.sharethis').show();
					//li.addClass('accepted');
					location.href = location.href + "#offer" + data;
				},
				error: function(data){
					li.find('.loading').hide();
					t.fadeIn();
					li.removeClass('accepted');
					p.find('.error').html('Sorry there was a problem confirming your offer, please try again').slideDown().delay('3000').slideUp();
				}
			});
		}
	})


	$('#updateform').bind('submit',function(){
		var pass = true;
		var error = "";
		$('input').each(function(){ $(this).removeClass('req') });
		var tel = $('#data_telephone').val(); var mob = $('#data_mobile').val();

		if($('#data_firstname').val() == ""){ $('#data_firstname').addClass('req'); pass = false; }
		if($('#data_lastname').val() == ""){ $('#data_lastname').addClass('req'); pass = false; }
		if($('#data_emailaddress').val() == "" || (!IsEmail($('#data_emailaddress').val())) ){ $('#data_emailaddress').addClass('req'); pass = false; }
		if($('#data_password').val() == ""){ $('#data_password').addClass('req'); pass = false; }

		if(tel != ""){ if(tel != "" && tel[0] == "0"){ pass = true; } else { $('#data_telephone').addClass('req'); pass = false; } }
		if(mob != ""){ if(mob != "" && mob[0] == "0"){ pass = true; } else { $('#data_mobile').addClass('req'); pass = false; } }

		if(pass){
			$.ajax({
				url: "/process/form-update-account.php",
				cache: false,
				type: "POST",
				data: $('#updateform').serializeArray(),
				success: function(data){
					$('#updateform').html(data);
				},
				error: function(data){
					$('#updateform').find('.error').html('Sorry there was a problem updating your details, please try again').slideDown().delay('3000').slideUp();
				}
			});
		}
	})

	$('#joinform').bind('submit',function(){
		var pass = true;
		var error = "";
		$('input').each(function(){ $(this).removeClass('req') });

		if($('#data_firstname').val() == ""){ $('#data_firstname').addClass('req'); pass = false; }
		if($('#data_lastname').val() == ""){ $('#data_lastname').addClass('req'); pass = false; }
		if($('#data_emailaddress').val() == "" || (!IsEmail($('#data_emailaddress').val())) ){ $('#data_emailaddress').addClass('req'); pass = false; }
		if($('#data_password').val() == ""){ $('#data_password').addClass('req'); pass = false; }

		if(pass){
			$.ajax({
				url: "/process/form-register.php",
				cache: false,
				type: "POST",
				data: $('#joinform').serializeArray(),
				success: function(data){
					location.href = "/offers/most-wanted";
				},
				error: function(data){
					$('#joinform').find('.error').html('Sorry there was a problem registering your details, please try again').slideDown().delay('3000').slideUp();
				}
			});
		}
	})

	$('#loginform').bind('submit',function(){
		var pass = true;
		var error = "";
		$('input').each(function(){ $(this).removeClass('req') });

		if($('#login_emailaddress').val() == "" || (!IsEmail($('#login_emailaddress').val())) ){ $('#login_emailaddress').addClass('req'); pass = false; }
		if($('#login_password').val() == ""){ $('#login_password').addClass('req'); pass = false; }

		if(pass){
			$.ajax({
				url: "/process/form-login.php",
				cache: false,
				type: "POST",
				data: $('#loginform').serializeArray(),
				success: function(data){
					time = (3600 * 1000 * 24 * 365);
					if($('#loginform input[type=checkbox]').prop('checked')){
						setCookie('signups_cookie_login',data,time);
					} else {
						setCookie('signups_cookie_login','',time);
					}
					location.reload();
				},
				error: function(data){
					$('#loginform').find('.error').html('Sorry there was a problem finding your details, please try again').slideDown().delay('3000').slideUp();
				}
			});
		}
	})

	$('#passwordform').bind('submit',function(){
		var pass = true;
		var error = "";
		$('input').each(function(){ $(this).removeClass('req') });

		if($('#pass_emailaddress').val() == "" || (!IsEmail($('#pass_emailaddress').val())) ){ $('#pass_emailaddress').addClass('req'); pass = false; }

		if(pass){
			$.ajax({
				url: "/process/form-password.php",
				cache: false,
				type: "POST",
				data: $('#passwordform').serializeArray(),
				success: function(data){
					location.href = data;
				},
				error: function(data){
					$('#passwordform').find('.error').html('Sorry there was a problem finding your details, please try again').slideDown().delay('3000').slideUp();
				}
			});
		}
	})

	$('a.poplogin').fancybox({
		fitToView	: false,
		width: '480px',
		height: '200px',
		autoSize: true,
		scrolling: 'no',
		closeClick: false
	});

	$('a.popregister').fancybox({
		fitToView	: false,
		width: '480px',
		height: '400px',
		autoSize: true,
		scrolling: 'no',
		closeClick: false
	});

	$('footer li a').click(function(){
		if($.browser.mobile){
			if($(this).attr('href') == "/offers"){
				$('footer li.offer').show();
				return false;
			}
		}
	})

	if($.browser.mobile){
		if(location.href.indexOf('/offers/') > -1){
			$('footer li.offer').show();
		}
	}

});

$(window).resize(function(){
	resizeHeader();
})

//-->