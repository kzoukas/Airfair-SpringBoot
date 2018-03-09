(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("sideBar", {
        templateUrl: 'js/components/side-bar/sidebar.html',
        controllerAs: "model",
        controller: sideBarController
    });
    sideBarController.$inject=['$scope','$location','parameterService','$route'];
    function sideBarController($scope,$location,parameterService,$route){
        var model = this;
        model.$onInit=initialize;
        model.stops=1;
        model.maxPrice=300;
        model.flightDuration=10;
        model.connectingTime=4;
        $scope.filter = {
            stops:"1",
            maxPrice:300,
            flightDuration:10,
            connectingTime:4
        };
        function initialize() {

        }
        $scope.submitFilters=function() {
            model.params=parameterService.getter();
            model.stops=$scope.filter.stops;
            model.maxPrice=$scope.filter.maxPrice ;
            model.flightDuration=$scope.filter.flightDuration;
            model.connectingTime=$scope.filter.connectingTime;
            $location.path('/allflights').search({from:model.params.from,to:model.params.to,checkIn:model.params.checkIn,typeOfFlight:model.params.typeOfFlight,adults:model.params.adults,childs:model.params.childs,stops:model.stops,maxPrice:model.maxPrice,flightDuration:model.flightDuration,connectingTime:model.connectingTime});
        }
        model.params=parameterService.getter();
        $location.path('/allflights').search({from:model.params.from,to:model.params.to,checkIn:model.params.checkIn,typeOfFlight:model.params.typeOfFlight,adults:model.params.adults,childs:model.params.childs,stops:model.stops,maxPrice:model.maxPrice,flightDuration:model.flightDuration,connectingTime:model.connectingTime});
    };
}());