#<b>API Object: OWF.Preferences.getUserPreference(cfg)</b>#

#####<i>Definition:</i> 
Use the `/getUserPreference` API to return a user preference based on its namespace and name. The preference could be user metadata such as name, email, etc.
 

#####<i>How it works:</i>#####
A system makes a call to another system that asks to reference data instead of return it. To make this call, one of the following REST Calls will request user data from the user profile URI [IS THIS RIGHT?] 

#####<i>Request URL:</i>#####

https://localhost:8443/owf/prefs/preference/com.company.widget/First%20President?version=7.15.0-v1&dojo.preventCache=1433176043577

#####<i>Request Method:</i>#####
GET, Form_Method:none

#####<i>Requirements:</i>#####
The call must include namespace. Name is optional.

- If name is a non-null empty string, it will return all preferences with a matching namespace.
- If namespace is a non-null empty string, it will throw an HTML 404 error, “The requested resource is not available.”
- If name or namespace are null it will fill them in with “undefined” and perform the request using 'undefined' as the name or namespace.


#####<i>Response:</i>#####
When you provide the namespace and name and the system finds a matching preference, it will return the preference information:

    {"id":157,"namespace":"com.company.widget","path":"First President","value":"fooval","user":{"userId":"testUser1"}}


#####<i>How to use it:</i>#####
This API provides additional metadata about a preference.

<hr>
###<b>Example</b>###


The following is an example of a call to get a user preference:

    {
      "success": true,
      "results": 3,
      "rows": [
        {
          "id": 144,
          "namespace": "com.company.widget",
          "path": "First President",
          "value": "foovalue",
          "user": {
            "userId": "testUser1"
          }
        },
        {
          "id": 146,
          "namespace": "com.company.widget",
          "path": "Second President",
          "value": "foovalue",
          "user": {
            "userId": "testUser2"
          }
        },
        {
          "id": 157,
          "namespace": "com.company.widget",
          "path": "First Vice President",
          "value": "fooval",
          "user": {
            "userId": "testAdmin1"
          }
        }
      ]
    }


If the system does <b>not</b> find a matching preference, it will return:

`{"success":true,"preference":null}`

Example:

    function onSuccess(pref) {alert(pref.value);}
    function onFailure(error,status) {
	    alert('Error ' + error);
	    alert(status);
    }
    var cfg = {
        namespace: 'com.company.widget',
        name: 'First President',
        onSuccess: onSuccess,
        onFailure: onFailure
    };

    OWF.Preferences.getUserPreference(cfg);



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
<table style="width:75%">
  <thead>
    <td><b>Error</b></td>
    <td><b>Action</b></td>
  </thead>
  <tr>
    <td>HTTP Status 404: “The requested resource is not available”</td>
    <td>Enter a value in the namespace.</td> 
  </tr> 
</table> 
