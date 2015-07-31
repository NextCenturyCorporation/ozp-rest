#API Object: marketplace.search

##Definition 
Use the `/search` API to create, update, read or delete a search in the system. 

##Resource Information
The following properties appear in the Type JSON:

<table style="width:100%">
    <thead>
        <tr>
            <td><b>Parameter</b></td>
            <td><b>Description</b></td
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>total</td>
            <td>The numerber of results</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/listing/search?queryString={Search%Term}`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Method
###<a name=GET>GET</a>###
Use this call to **read or view** a search in the system.
#####Request

`https://localhost:8443/marketplace/api/listing/search?queryString={Search%Term}`

Marketplace (i.e., Center) returns the representation of the results that matches the{Search Term}, as shown in the Response. 

#####Response Code:
200

#####Response from the search

	{  
    	"total":0,
    	"_links":{  
    	    "self":{  
    	        "href":"https://localhost:8443/marketplace/api/listing/search?queryString=%22Air+Mail%22&sort=score&order=DESC&max=24&offset=0"
    	    }
    	}
	}

#####Requirements
none



###Possible Errors

This table lists common errors. Other errors may occur but these are the most likely: 
#**UPDATE! UPDATE! UPDATE!!!**
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
            <td>400
            <td>Type cannot be deleted.
            <td>See if it is associated with a listing. If any listing is assigned to the type, you cannot delete that type.</td> 
        </tr>
        <tr>
            <td>403
            <td>User cannot create, edit, delete a type.
            <td>Only administrators can create, edit, delete types.</td> 
        </tr>  
        <tr>
            <td>400
            <td>Type cannot be created or updated.
            <td>The type must include all required fields.</td> 
        </tr>
        <tr>
            <td>400
            <td>Type must have a unique name.</td>
            <td>If the type name is not unique, a validation error occurs when you try to save.</td> 
        </tr>
    </tbody>
</table> 



