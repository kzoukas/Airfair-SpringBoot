(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("flightList", {
        templateUrl: 'js/components/flights-list/flight-list.html',
        controllerAs: "model",
        controller: flightListController
    });
    flightListController.$inject=['$scope','$window', '$routeParams','$interval', 'flightListService', 'parameterService'];
    function flightListController($scope,$window, $routeParams,$interval, flightListService, parameterService){

        var model = this;
        model.$onInit=initialize;
        function initialize() {

        }
        var params = getSearchParameters();
        function getSearchParameters() {
            var prmstr = $window.location.search.substr(1);
            return prmstr != null && prmstr != "" ? transformToAssocArray(prmstr) : {};
        }

        function transformToAssocArray(prmstr) {
            var params = {};
            var prmarr = prmstr.split("&");
            for (var i = 0; i < prmarr.length; i++) {
                var tmparr = prmarr[i].split("=");
                params[tmparr[0]] = tmparr[1];
            }
            return params;
        }

        parameterService.setter(params);


        model.params=parameterService.getter();
        var onRepo = function(data){
            $scope.flightList = data;
        };
        var onError = function(reason){
            $scope.error = reason;
        };
        callService();
        // $interval(callService,10000);
        function callService(){
            flightListService.getFlightList(model.params.from,model.params.to,model.params.stops,model.params.maxPrice,model.params.flightDuration)
                .then(onRepo, onError);
        }

    };

}());