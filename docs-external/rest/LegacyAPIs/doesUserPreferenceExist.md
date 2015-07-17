#<b>API Object: OWF.Preferences.doesUserPreferenceExist (cfg)</b>#

#####<i>Definition:</i> 
Use the `/doesUserPreferenceExist` API to query if they system includes a specific user preference. It will search based on preferences' namespaces and names. 

 

#####<i>How it works:</i>#####
A system makes a call to another system that asks to reference data instead of return it. To make this call, one of the following REST Calls will request user data from the user profile URI [IS THIS RIGHT?] 

#####<i>Request URL:</i>#####

https://localhost:8443/owf/prefs/hasPreference/com.company.widget/First%20President?version=7.15.0-v1&dojo.preventCache=1433169029635

#####<i>Request Method:</i>#####
GET, Form_Method:none

#####<i>Requirements:</i>#####
 The call must include <b>namespace and name </b>
 
- If namespace or name are null, it will change the null to 'undefined' and search for a namespace and/or name that is called 'undefined'.
- If name or namespace use an empty string that is not null, the query returns the HTML 404 error "The requested resource is not available." 

#####<i>Response:</i>#####

If there is a preference matching the namespace and name it will return:

`{"preferenceExist":true,"statusCode":200}`

If there is not a matching preference it will return:

`{"preferenceExist":false,"statusCode":200}`


#####<i>How to use it:</i>#####
Use it to find user preferences.


<hr>
###<b>Example</b>###


The following is an example of a call to delete user preference:


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

    OWF.Preferences.doesUserPreferenceExist(cfg);



###Resource Information###
<table style="width:100%">
  <thead>
    <td>Parameter</td>
    <td>Description</td
  </thead>
  <tr>
    <td>{Object} cfg</td>
    <td>Use the following properties to configure the object. EXAMPLE???</td> 
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
    <td>HTTP Status 404: “The requested resource is not available”</td>
    <td>Enter a preference name and namespace.</td> 
  </tr> 
</table> 
