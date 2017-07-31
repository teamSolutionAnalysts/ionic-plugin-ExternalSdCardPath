# Cordova Android external SD card path plugin

Plugin that returns the path of internal and external sd card.

## Using
    
Install the plugin

    $ cordova plugin add https://github.com/teamSolutionAnalysts/ionic-plugin-ExternalSdCardPath.git
    


```js
    var success = function(message) {
        var path = JSON.parse(message);
        console.log(path.SDCard.external);
    }

    var failure = function() {
        alert("Error calling external Plugin");
    }

    externalsd.getPath("microSD", success, failure);
```
    
   You can access path of external and internal by following this way:
   
    1) path.SDCard.external 
    2) path.SDCard.internal 

Install Android platform

    cordova platform add android
    

## More Info

For more information on setting up Cordova see [the documentation](http://cordova.apache.org/docs/en/latest/guide/cli/index.html)

For more info on plugins see the [Plugin Development Guide](http://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/index.html)

