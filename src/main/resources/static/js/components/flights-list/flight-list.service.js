//
// (function () {
//     'use strict';
//     var module = angular.module("flightTicketApp");
//     module.factory('flightListService', ['$resource', function ($resource) {
//         return $resource('/allflights',
//             {
//
//             }
//         );
//     }]);
// }());
(function(){

    var flightListService = function($http){
        var getFlightList = function(fromIata,toIata,stops,maxPrice,flightDuration){
            var repoUrl = "/allflights/" + fromIata +"/"+ toIata +"/"+ stops +"/"+ maxPrice +"/"+ flightDuration;
            return $http.get(repoUrl)
                .then(function(response){
                   return response.data;
                });
        };
        return {
            getFlightList: getFlightList

        };
    };
    var module = angular.module("flightTicketApp");
    module.factory("flightListService", flightListService);

}());
