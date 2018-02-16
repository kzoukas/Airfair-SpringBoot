// (function(){
//
//     var filterService = function($http){
//         var getFilters = function(filter){
//
//             var repoUrl = "/allflights/filters";
//             return $http.get(repoUrl)
//                 .then(function(response){
//                     return response.data;
//                 });
//         };
//         return {
//             getFilters: getFilters
//
//         };
//     };
//     var module = angular.module("flightTicketApp");
//     module.factory("filterService", filterService);
//
// }());
