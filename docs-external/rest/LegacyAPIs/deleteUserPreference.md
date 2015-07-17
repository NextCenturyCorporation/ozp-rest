#<b>API Object: OWF.Preferences.deleteUserPreference(cfg)</b>#

#####<i>Definition:</i> 
Use the `/deleteUserPreference` API to remove a user preference based on its namespace and name. The preference could be user metadata such as name, email, etc.

 

#####<i>How it works:</i>#####
A system makes a call to another system that asks to reference data instead of return it. To make this call, one of the following REST Calls will request user data from the user profile URI [IS THIS RIGHT?] 

#####<i>Request URL:</i>#####

https://localhost:8443/marketplace/api/prefs/preference/com.company.widget/First%20President

#####<i>Request Method:</i>#####
POST, Form_Method:Delete

#####<i>Requirements:</i>#####
 The call must include <b>namespace and name </b>
 
- If namespace or name are null, it will change the null to 'undefined' and search for a namespace and/or name that is called 'undefined'.
- If name is “” (empty but not null). It will not throw an error but will also not delete anything.

- If namespace is “” (empty but not null) it will throw an error since the url will be “prefs/preference//name” and that is not a valid url.

#####<i>Response:</i>#####

If the system could not find a matching preference and there was not an error it returns:<br>

`{“success”:true, “preference”:null}`

If a preference was deleted the system returns:<br>
`{"id":7,"namespace":”com.company.widget”,"path":"First President","value":"foo val","user":{"userId":"testUser1"}}`


#####<i>How to use it:</i>#####
Use it to delete user preferences.



<hr>
###<b>Example</b>###


The following is an example of a call to delete user preference:


    function onSuccess(pref){
    	alert(pref.value);
    }
    
    function onFailure(error,status){
    	alert('Error ' + error);
    	alert(status);
    }
    

    var cfg = {
	    namespace: 'com.company.widget',
	    name: 'First President',
	    onSuccess: onSuccess,
	    onFailure: onFailure
    };


    OWF.Preferences.deleteUserPreference(cfg);



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
    <td>HTTP Status 403: Access to the specified resource () has been forbidden” when namespace is blank.</td>
    <td>Enter a preference name in the namespace.</td> 
  </tr> 
</table> 
