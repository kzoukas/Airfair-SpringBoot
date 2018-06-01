(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("topBar", {
        templateUrl: 'js/components/top-bar/top-bar.html',
        controllerAs: "model",
        controller: topBarController
    });
    topBarController.$inject=['$scope','topBarService','$route'];
    function topBarController($scope,topBarService,$route){

        var model = this;
        $scope.submitNewSearch=function() {


            topBarService.getRedirectPage().then(onSuccess,onError);

            var onSuccess = function(data){
                if (data === "null") {
                    data = null;
                }
                return data;
            };
            var onError = function(reason){
                $scope.error = reason;
            };

        }
    };
}());