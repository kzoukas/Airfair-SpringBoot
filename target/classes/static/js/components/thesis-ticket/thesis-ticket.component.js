(function(){
    "use strict";
    var module = angular.module("thesisApp");
    module.component("thesisTicket", {
        templateUrl: 'js/components/thesis-ticket/thesis-ticket.component.html',
        controllerAs: "model",
        controller: ThesisTicketController
    });
    ThesisTicketController.$inject = ['$http'];

    function ThesisTicketController($http) {
        var model = this;
        var msg = "Hello World Local";
        model.msg = "Hello World";

        model.thesisTicket = [];
        model.getAll = getAll;


        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/allFlights";
            var thesisTicketPromise = $http.get(url);
            thesisTicketPromise.then(function(response){
                model.thesisTicket = response.data;
            });
        }


        // FlightTicket.query(flightTicketResponse, flightTicketErrResponse);
        //
        // function flightTicketResponse(response) {
        //     model.listOfFlights = response;
        // }
        //
        // function flightTicketErrResponse(errResponse){
        //
        // }
        //
        // model.updateSearch = function(){
        //     CustomFlightTicket.query({flightFrom: model.formFlightFrom,flightTo: model.formFlightTo},customResponse, customErrResponse)
        // };
        //
        // function customResponse(customResp){
        //     model.listOfFlights = customResp;
        // }
        //
        // function customErrResponse(errCustomResp){
        //
        // }



    }
}());