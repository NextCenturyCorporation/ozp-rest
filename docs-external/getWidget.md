#<b>API Object: OWF.Preferences.getWidget(cfg)</b>#

#####<i>Definition:</i> 
Use the `/getWidget` API to return a Widget based on its widget ID. 
 

#####<i>How it works:</i>#####
A system makes a call to OWF asking if a widget exists. To make this call, one of the following REST Calls will request a widget ID.  

#####<i>Request URL:</i>#####

https://localhost:8443/owf/prefs/widget/WIDGETID

#####<i>Request Method:</i>#####
GET, Form_Method:none

#####<i>Requirements:</i>#####
widgetID

Optional: none

#####<i>Response:</i>#####
OWF returns the matching widgetID, as shown below. However, if the widgetID is an empty string (""), OWF will return a list of all widgets.

    {
      "id": 39,
      "namespace": "widget",
      "value": {
        "originalName": "Channel Shouter",
        "universalName": null,
        "editable": true,
        "disabled": false,
        "visible": true,
        "favorite": false,
        "groupWidget": false,
        "position": 10,
        "userId": "testAdmin1",
        "userRealName": "Test Admin 1",
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
        "groups": [
          
        ],
        "tags": [
          
        ],
        "definitionVisible": true,
        "singleton": false,
        "background": false,
        "descriptorUrl": null,
        "allRequired": [
          
        ],
        "directRequired": [
          
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
    }





#####<i>How to use it:</i>#####
Use it to find specific information about a widget including its Descriptor URL (if it has one) and its universal name. 

<hr>
###<b>Example</b>###


The following is an example of a call to get a widget ID:

    var onSuccess = function(obj) {
        if (obj.value) {
            alert(obj.value.namespace);
        }
    };

    var onFailure = function(error) {
        alert(error);
    };

    OWF.Preferences.getWidget({
        widgetId:'eb5435cf-4021-4f2a-ba69-dde451d12551',
        onSuccess:onSuccess,
        onFailure:onFailure
    });

    



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
    <td>This will happen when getWidget is called using a widgetId that doesn't exist.
HTTP Status 404: “...Widget with guid of undefined not found.”
</td>
    <td>Make sure widgetId is set to a non-null string.</td> 
  </tr> 
</table> 
