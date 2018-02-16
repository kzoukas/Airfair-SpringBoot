(function(){

    var flightInfoService = function($http){
        var getFlightInfo = function(){
            var repoUrl = "/flightInfo";
            return $http.get(repoUrl)
                .then(function(response){
                    return response.data;
                });
        };
        return {
            getFlightInfo: getFlightInfo

        };
    };
    var module = angular.module("flightTicketApp");
    module.factory("flightInfoService", flightInfoService);

}());


// (function () {
//     'use strict';
//     var module = angular.module("flightTicketApp");
//     module.factory('flightInfoService', ['$resource', function ($resource) {
//         return $resource('/flightInfo',
//             {
//                 getFlightInfo: {method: 'GET'}
//             }
//         );
//     }]);
// }());
