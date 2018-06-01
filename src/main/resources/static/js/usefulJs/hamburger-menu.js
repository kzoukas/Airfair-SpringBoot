jQuery(function($) {
    "use strict";
    if ($('.hamburger-menu-wrapper').hasClass('open') === false) {
        $('.hamburger-menu').on('click', function () {
            $('.hamburger-menu-wrapper').toggleClass('open');
            $('body').toggleClass('show-nav');
        });
        $('.searchFilters').on('click', function () {
            $('.hamburger-menu-wrapper').toggleClass('open');
            $('body').toggleClass('show-nav');
        });
        // $('body').on('click', function (event) {
        //     if (!$('.sidebar-block').is(event.target) && $('.sidebar-block').has(event.target).length === 0) {
        //
        //         $('.hamburger-menu-wrapper').toggleClass('open');
        //         $('body').toggleClass('show-nav');
        //
        //     }
        // });
    }

});
