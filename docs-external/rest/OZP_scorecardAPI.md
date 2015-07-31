#API Object: marketplace.scorecard

##Definition 
Use the `/scorecard` API to create, update, read or delete scorecard data in the system. 

##Resource Information
The following properties appear in the scorecard JSON:

<table style="width:100%">
    <thead>
        <tr>
            <td><b>Parameter</b></td>
            <td><b>Description</b></td
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>description</td>
            <td>An explanation of the scorecard field.</td> 
        </tr>
        <tr>
            <td>image</td>
            <td>The icon associated with the scorecard question.</td> 
        </tr>
        <tr>
            <td>showOnListing</td>
            <td>This field tells the system if the scorecard question should appear on the listing or be hidden from the UI.</td> 
        </tr>
        <tr>
            <td>question</td>
            <td>In this field, enter the actual question that will appear on the listing.</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/scorecard`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_scorecardAPI.md#POST),
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_scorecardAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_scorecardAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_scorecardAPI.md#DELETE)
<br>
Each method will be explained in the following sections:

###<a name=POST>POST</a>
Use this call to **create** a scorecard question in the system.

#####Request
`https://localhost:8443/marketplace/api/scorecard`

	{  
    	"description":"DESCRIPTION",
    	"image":null,
    	"showOnListing":false,
    	"question":"Description"
	}

#####Response Code:
201

#####Response

	{  
    	"question":"Description",
    	"description":"DESCRIPTION",
    	"image":null,
    	"showOnListing":false,
    	"_links":{  
    	    "self":{  
    	        "href":"https://localhost:8443/marketplace/api/scorecard/1"
    	    }
    	}
	}

#####Requirements
none
<br>




###<a name=PUT>PUT</a>###
Use this call to **update** a scorecard question in the system.
#####Request
`https://localhost:8443/marketplace/api/scorecard/{id}`

	{  
    	"id":27,
    	"description":"DESCRIPTION",
    	"image":null,
    	"showOnListing":false,
    	"question":"Description"
	}

#####Response Code:
200

#####Response
    
	{
    	"question":"Description",
    	"description":"DESCRIPTION",
    	"image":null,
    	"showOnListing":false,
    	"_links":
    	{
    	    "self":
    	    {
    	        "href":"https://localhost:8443/marketplace/api/scorecard/27"
    	    }
    	}
	}

#####Requirements
none
<br>
<br>


###<a name=GET>GET</a>###
Use this call to **read or view** a scorecard question in the system.
#####Request
`https://localhost:8443/marketplace/api/scorecard/{id}`
 

#####Response Code:
200

#####Response

	{
    	"question":"123456",
    	"description":"DESCRIPTION",
    	"image":null,
    	"showOnListing":false,
    	"_links":
    	{
    	    "self":
    	    {
    	        "href":"https://localhost:8443/marketplace/api/scorecard/2"
    	    }
    	}
	}

#####Requirements
none


###<a name=DELETE>DELETE</a>###
Use this call to remove a scorecard question from the system.
#####Requirements
`https://localhost:8443/marketplace/api/scorecard/{id}`

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



