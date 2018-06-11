(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("topBar", {
        templateUrl: 'js/components/top-bar/top-bar.html',
        controllerAs: "model",
        controller: topBarController
    });
    topBarController.$inject=['$scope','topBarService','$route','redirect_url'];
    function topBarController($scope,topBarService,$route,redirect_url){

        var model = this;
        $scope.node_redirect=redirect_url;

    };
}());