app.directive("viewpart",  function($compile) {
    return {
    	restrict: 'E',
        replace: true,
        link: function (scope, elem, attrs) {
            console.log("now initializing viewpart " + attrs.id + " with directiveName " + attrs.directivename);
            var pm = { attributes: {} };
            scope.directiveName = attrs.directivename;
            
            var directiveName = scope.directiveName; // scope.pm.attributes.type;
            var templateDirective = '<' + directiveName + '/>';
            var myTemplate = $compile(templateDirective)(scope);
            elem.append(myTemplate);
        }
    };
 });