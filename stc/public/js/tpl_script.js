'use strict';

$(document).ready(() => {
  var $window = $(window);
  // add id to main menu for mobile menu start
  var getBody = $('body');
  var bodyClass = getBody[0].className;
  $('.main-menu').attr('id', bodyClass);
  // add id to main menu for mobile menu end

  // card js start
  $('.card-header-right .close-card').on('click', function () {
    var $this = $(this);
    $this.parents('.card').animate({
      'opacity': '0',
      '-webkit-transform': 'scale3d(.3, .3, .3)',
      'transform': 'scale3d(.3, .3, .3)'
    });

    setTimeout(() => {
      $this.parents('.card').remove();
    }, 800);
  });

  $('.card-header-right .minimize-card').on('click', function () {
    var $this = $(this);
    var port = $($this.parents('.card'));
    var card = $(port).children('.card-block').slideToggle();
    $(this).toggleClass('icon-minus').fadeIn('slow');
    $(this).toggleClass('icon-plus').fadeIn('slow');
  });
  $('.card-header-right .full-card').on('click', function () {
    var $this = $(this);
    var port = $($this.parents('.card'));
    port.toggleClass('full-card');
    $(this).toggleClass('icon-maximize');
    $(this).toggleClass('icon-minimize');
  });

  $('#more-details').on('click', () => {
    $('.more-details').slideToggle(500);
  });
  $('.mobile-options').on('click', () => {
    $('.navbar-container .nav-right').slideToggle('slow');
  });
  // card js end
  $.mCustomScrollbar.defaults.axis = 'yx';
  $('#styleSelector .style-cont').slimScroll({
    setTop: '10px',
    height: 'calc(100vh - 440px)'
  });
  $('.main-menu').mCustomScrollbar({
    setTop: '10px',
    setHeight: 'calc(100% - 80px)'
  });

  // $(".search-btn").on('click', function() {
  //     $(".main-search").addClass('open');
  //     $('.main-search .form-control').animate({
  //         'width': '200px',
  //     });
  // });
  // $(".search-close").on('click', function() {
  //     $('.main-search .form-control').animate({
  //         'width': '0',
  //     });
  //     setTimeout(function() {
  //         $(".main-search").removeClass('open');
  //     }, 300);
  // });

  $('#mobile-collapse i').addClass('icon-toggle-right');
  $('#mobile-collapse').on('click', () => {
    $('#mobile-collapse i').toggleClass('icon-toggle-right');
    $('#mobile-collapse i').toggleClass('icon-toggle-left');
  });
});

$(document).ready(() => {
  $(() => {
    $('[data-toggle="tooltip"]').tooltip();
  });
  $('.theme-loader').fadeOut('slow', function () {
    $(this).remove();
  });
});

// toggle full screen
function toggleFullScreen() {
  var a = $(window).height() - 10;
  if (!document.fullscreenElement // alternative standard method
    && !document.mozFullScreenElement && !document.webkitFullscreenElement) { // current working methods
    if (document.documentElement.requestFullscreen) {
      document.documentElement.requestFullscreen();
    } else if (document.documentElement.mozRequestFullScreen) {
      document.documentElement.mozRequestFullScreen();
    } else if (document.documentElement.webkitRequestFullscreen) {
      document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
    }
  } else if (document.cancelFullScreen) {
    document.cancelFullScreen();
  } else if (document.mozCancelFullScreen) {
    document.mozCancelFullScreen();
  } else if (document.webkitCancelFullScreen) {
    document.webkitCancelFullScreen();
  }
  $('.full-screen').toggleClass('icon-maximize');
  $('.full-screen').toggleClass('icon-minimize');
}

window.toggleFullScreen = toggleFullScreen;
