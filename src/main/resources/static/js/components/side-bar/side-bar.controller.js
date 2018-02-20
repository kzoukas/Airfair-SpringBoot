(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("sideBar", {
        templateUrl: 'js/components/side-bar/sidebar.html',
        controllerAs: "model",
        controller: sideBarController
    });

    sideBarController.$inject=['$scope','$location','parameterService','getFlightListFiltered','$route'];

    function sideBarController($scope,$location,parameterService,getFlightListFiltered,$route){

        var model = this;
        model.$onInit=initialize;
        model.stops=2;
        model.maxPrice=99;
        model.flightDuration=8;

        function initialize() {

        }


        $scope.submitFilters=function() {

            model.params=parameterService.getter();
            model.stops=$scope.filter.stops;
            model.maxPrice=$scope.filter.maxPrice ;
            model.flightDuration=$scope.filter.flightDuration;
            $location.path('/allflights').search({from:model.params.from,to:model.params.to,stops:model.stops,maxPrice:model.maxPrice,flightDuration:model.flightDuration});


        }
        // $scope.initFilter();
        //     alert($scope.filter.maxPrice);





        model.params=parameterService.getter();
        $location.path('/allflights').search({from:model.params.from,to:model.params.to,stops:model.stops,maxPrice:model.maxPrice,flightDuration:model.flightDuration});
        // $route.reload();
        // var onRepo = function(data){
        //     $scope.flightList = data;
        // };
        // var onError = function(reason){
        //     $scope.error = reason;
        // };
        // // callService();
        // // $interval(callService,10000);
        //
        //
        //     getFlightListFiltered.getFlightListFiltered(model.params.from , model.params.to)
        //         .then(onRepo,onError);


    };
}());