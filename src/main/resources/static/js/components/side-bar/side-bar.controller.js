(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("sideBar", {
        templateUrl: 'js/components/side-bar/sidebar.html',
        controllerAs: "model",
        controller: sideBarController
    });

    sideBarController.$inject=['$scope','parameterService','getFlightListFiltered'];

    function sideBarController($scope,parameterService,getFlightListFiltered){

        // var model=this;
        // model.$onInit=initialize;
        //
        // function initialize(){
        //
        //     model.filterParams();
        // }
        // $scope.initFilter=function() {

            // $scope.filter = {
            //     maxPrice: 8,
            //     flightDuration: 1000,
            //     stops: 1
            // }
            // $scope.filter.maxPrice=2;
        // }
        // $scope.initFilter();
        var model = this;

        model.params=parameterService.getter();
        var onRepo = function(data){
            $scope.flightList = data;
        };
        var onError = function(reason){
            $scope.error = reason;
        };
        // callService();
        // $interval(callService,10000);


            getFlightListFiltered.getFlightListFiltered(model.params.from , model.params.to)
                .then(onRepo,onError);


    };
}());