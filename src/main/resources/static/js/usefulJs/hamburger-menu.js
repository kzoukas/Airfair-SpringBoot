jQuery(function($) {
    "use strict";

        $('.hamburger-menu').on('click', function () {
            $('.hamburger-menu-wrapper').toggleClass('open');
            $('body').toggleClass('show-nav');
        });
        $('.searchFilters').on('click', function () {
            if ($('.hamburger-menu-wrapper').hasClass('open')) {
                $('.hamburger-menu-wrapper').toggleClass('open');
                $('body').toggleClass('show-nav');


            }
            $('body,html').animate({
                scrollTop: 0
            }, 900);

        });
        // $('body').on('click', function (event) {
        //     if (!$('.sidebar-block').is(event.target) && $('.sidebar-block').has(event.target).length === 0) {
        //
        //         $('.hamburger-menu-wrapper').toggleClass('open');
        //         $('body').toggleClass('show-nav');
        //
        //     }
        // });


});
