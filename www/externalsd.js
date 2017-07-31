/*global cordova, module*/

module.exports = {
    getPath: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "ExternalSd", "getPath", [name]);
    }
    // getSDCardPath: function (name, successCallback, errorCallback) {
    //     cordova.exec(successCallback, errorCallback, "ExternalSd", "ExternalSdCardPath", [name]);
    // }
    
};
