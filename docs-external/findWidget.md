#<b>API Object: OWF.Preferences.findWidget(cfg)</b>#

#####<i>Definition:</i> 
Use the `/findWidget` API to return a Widget data for every widget in OWF. 
 

#####<i>How it works:</i>#####
A system makes a call to OWF asking for metadata about every widget. To make this call, one of the following REST Calls will request widget data including the widget's associations with users and groups.  

#####<i>Request URL:</i>#####

https://localhost:8443/marketplace/prefs/widget/listUserAndGroupWidgets

#####<i>Request Method:</i>#####
POST <BR> 
Request Form Data <br> 
_method: GET <br>

    widgetName: 'widget's name'
    universalName: 'widget's universal name'
    widgetVersion: 'widget's version'
    widgetGuid: 'widget's guid'



#####<i>Requirements:</i>#####
None <br>

Optional: widgetName, universalName, widgetVersion, and widgetGuid (Must be inside searchParams object, see example below)

#####<i>Response:</i>#####

Returns a list of all widgets matching the given search parameters:

    [
      {
        "id": "eb5435cf-4021-4f2a-ba69-dde451d12551",
        "namespace": "widget",
        "value": {
          "universalName": null,
          "namespace": "Channel Shouter",
          "description": null,
          "url": "examples/walkthrough/widgets/ChannelShouter.gsp",
          "headerIcon": "themes/common/images/widget-icons/ChannelShouter.png",
          "image": "themes/common/images/widget-icons/ChannelShouter.png",
          "smallIconUrl": "themes/common/images/widget-icons/ChannelShouter.png",
          "largeIconUrl": "themes/common/images/widget-icons/ChannelShouter.png",
          "width": 295,
          "height": 250,
          "x": 0,
          "y": 0,
          "minimized": false,
          "maximized": false,
          "widgetVersion": "1.0",
          "totalUsers": 4,
          "totalGroups": 1,
          "tags": [
            
          ],
          "singleton": false,
          "visible": true,
          "background": false,
          "descriptorUrl": null,
          "definitionVisible": true,
          "directRequired": [
            
          ],
          "allRequired": [
            
          ],
          "intents": {
            "send": [
              
            ],
            "receive": [
              
            ]
          },
          "widgetTypes": [
            {
              "id": 1,
              "name": "standard",
              "displayName": "standard"
            }
          ]
        },
        "path": "eb5435cf-4021-4f2a-ba69-dde451d12551"
      },
      …more widgets…
    ]




#####<i>How to use it:</i>#####
Use it to compile a comprehensive list of widgets in OWF or a list of widgets with specific metadata.  

<hr>
###<b>Example</b>###


The following is an example of a call to find widgets:

    function onSuccess(pref) {alert(pref.value)}
    function onFailure(error,status) {
    	alert('Error ' + error)
    	alert(status)
    }
    var cfg = {
    	searchParams:{
    		widgetName: 'Channel Shouter'
    	}
    }
    OWF.Preferences.findWidgets(cfg);




###Resource Information###
<table style="width:100%">
  <thead>
    <td>Parameter</td>
    <td>Description</td
  </thead>
  <tr>
    <td>{Object} cfg</td>
    <td>Use the following properties to configure the object.</td> 
    </tr>
  <tr>
    <td>{String} cfg.namespace</td>
    <td>The namespace of the user preference.</td> 
  </tr>
  <tr>
    <td>{String} cfg.name</td>
    <td>The name of the user preference.</td> 
  </tr>
  <tr>
    <td>{Function} cfg.onSuccess</td>
    <td>The function that will be called if the user preference is successfully deleted from the database.</td> 
  </tr>
  <tr>
    <td>{Function} cfg.onFailure <i>Optional</i></td>
    <td>The system returns this function if the user preference cannot be deleted from the database or if the preference does not exist. If this function is not specified a default error message display.<br> 
    This function passes back the following parameters: <br>error: String
    <br>Status: The HTTP Status code</td> 
  </tr>
</table>


###<b>Possible Errors</b>###
<table style="width:100%">
  <thead>
    <td><b>Error</b></td>
    <td><b>Action</b></td>
  </thead>
  <tr>
    <td>No known errors at this time
</td>
    <td>N/A</td> 
  </tr> 
</table> 
