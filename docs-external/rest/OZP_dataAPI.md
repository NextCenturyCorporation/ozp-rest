#API Object: marketplace.data

##Definition 
Use the `/data` API to create, update, read or delete data in the system. 

##Resource Information
The following properties appear in the Data JSON:

<table style="width:100%">
    <thead>
        <tr>
            <td><b>Parameter</b></td>
            <td><b>Description</b></td
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>id</td>
            <td>The numerical ID associated with the type object.</td> 
        </tr>
        <tr>
            <td>description</td>
            <td>Information about the type that is provided by an administrator.</td> 
        </tr>
        <tr>
            <td>title</td>
            <td>The name of the type.</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/data`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_dataAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_dataAPI.md#DELETE)
<br>
Each method will be explained in the following sections:


###<a name=GET>GET</a>###
Use this call to **read or view** data in the system.
#####Request
If you want to see an empty list enter:
`https://localhost:8443/marketplace/api/profile/2/data`


#####Response Code:
200

#####Response for an empty list of data

	{
    	"_links":
    	{
    	    "item":
    	    {
    	        "href":"https://localhost:8443/marketplace/api/profile/2/data/dashboard-data"
    	    },
    	    "self":
    	    {
    	        "href":"https://localhost:8443/marketplace/api/profile/2/data"
    	    }
    	}
	}



###<a name=DELETE>DELETE</a>###
Use this call to remove data from the system.
#####Requirements
`https://localhost:8443/marketplace/api/profile/2/data/{id}`

#####Response Code:
204

#####Response
	no content<br>    
       
#####Requirements
none

<br>
<br>




###Possible Errors

This table lists common errors. Other errors may occur but these are the most likely:
<table style="width:100%">
    <thead>
        <tr>    
            <td><b>Error <br> Code</b></td>
            <td><b>Error</b></td>
            <td><b>Troubleshooting</b></td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>400</td>
            <td>Key name validation error.</td>
            <td>Maximum name must not exceed 255 characters.</td> 
        </tr>
    </tbody>
</table> 



