(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("mainPage", {
        templateUrl: 'js/components/main-page/main-page.html',
        controllerAs: "model",
        controller: mainPageController
    });
    mainPageController.$inject=[];
    function mainPageController(){};
}());