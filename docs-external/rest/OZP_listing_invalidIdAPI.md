#API Object: marketplace.Listing_InvalidID

##Definition 
Use the `/Listing_InvalidID` API to update, read or delete a listing with an invalid ID in the system. 

##Request Method TOC
Each method will be explained in their respective section.
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_listing_invalidIdAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_listing_invalidIdAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_listing_invalidIdAPI.md#DELETE)
<br>


##Resource Information
The following properties appear in the Listing JSON:

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
            <td>The numerical ID associated with the listing.</td> 
        </tr>
        <tr>
            <td>type</td>
            <td>Name of the kind of technology associated with the listing (A steward creates a list of types from which the listing owner must choose).</td> 
        </tr>
        <tr>
            <td>description</td>
            <td>Information about the listing that is provided/updated by the owner or a steward.</td> 
        </tr>
        <tr>
            <td>singleton</td>
            <td>True/False field identifying if the listing can open once or multiple times in the Webtop.</td> 
        </tr>
        <tr>
            <td>imageXXXUrl parameters</td>
            <td>The locations where the small, medium, large and extra large icons reside. <i> Ensure the locations use secure addresses, e.g., https, not http </i> </td> 
        </tr>
        <tr>
            <td>agencyShort</td>
            <td>The abreviation for the associated agency.</td> 
        </tr>
        <tr>
            <td>currentRejection</td>
            <td>Is the listing approved or rejected by a steward?</td> 
        </tr>
        <tr>
            <td>required</td>
            <td>Are their any required fields.</td> 
        </tr>
        <tr>
            <td>tags</td>
            <td>Tags associated with the listing.</td> 
        </tr>
        <tr>
            <td>height</td>
            <td>When the listing opens in Webtop, how tall should it be?</td> 
        </tr>
        <tr>
            <td>width</td>
            <td>when a listing opens in Webtop, how wide should it be?</td> 
        </tr>
        <tr>
            <td>launchUrl</td>
            <td>The address where the listing resies. The system only allows secure (https) addresses.</td> 
        </tr>
        <tr>
            <td>uuid</td>
            <td>Universal Unique Identifier</td> 
        </tr>
        <tr>
            <td>whatIsNew</td>
            <td>Release Notes</td> 
        </tr>
        <tr>
            <td>isEnabled</td>
            <td>true/false value determining if the listing is only visable to the owner and stewards (disabled) or available for searching and bookmarking by everyone (enabled)</td> 
        </tr>
        <tr>
            <td>isFeatured</td>
            <td>The listing appears on the featured carousel on the Search and Discovery Page.</td> 
        </tr>
        <tr>
            <td>avgRate</td>
            <td>The averaged rating of all user ratings.</td> 
        </tr>
        <tr>
            <td>totalVotes</td>
            <td>The number of ratings.</td> 
        </tr>
        <tr>
            <td>totalRate1, etc.</td>
            <td>A list of rating.</td> 
        </tr>
        <tr>
            <td>contacts</td>
            <td>Any contacts associated with the listing.</td> 
        </tr>
        <tr>
            <td>agency</td>
            <td>The agency associated with the listing.</td> 
        </tr>
        <tr>
            <td>categories</td>
            <td>The categories associated with the listing.</td> 
        </tr>
        <tr>
            <td>owners</td>
            <td>The listing owner(s).</td> 
        </tr>
        <tr>
            <td>docUrls</td>
            <td>These fields link to a documentation resource and the user-friendly name associated with it in the UI.</td> 
        </tr>
        <tr>
            <td>intents</td>
            <td>location of any intents associated with the listing.</td> 
        </tr>
        <tr>
            <td>title</td>
            <td>Name of the listing.</td> 
        </tr>
        <tr>
            <td>approvedDate</td>
            <td>When the listing was approved.</td> 
        </tr>
        <tr>
            <td>versionName</td>
            <td>The version associated with the listing, this field is populated by the owner or a steward.</td> 
        </tr>
        <tr>
            <td>xxxIconId</td>
            <td>The UUID for each icon</td> 
        </tr>
        <tr>
            <td>descriptionShort</td>
            <td>100 character summary of the listing.</td> 
        </tr>
        <tr>
            <td>requirements</td>
            <td>List of tools the listing needs.</td> 
        </tr>
        <tr>
            <td>approvalStatus</td>
            <td>Displays listing status: in progress, approved, rejected.</td> 
        </tr>
        <tr>
            <td>totalComments</td>
            <td>Number of comments</td> 
        </tr>
        <tr>
            <td>screenshots</td>
            <td>IDs and URLs for associated screenshots</td> 
        </tr>
		<tr>
            <td>editedDate</td>
            <td>Date and time someone last edited the listing</td> 
        </tr>
		<tr>
            <td>ozp:activity</td>
            <td>This call returns the listing metadata</td> 
        </tr>
		<tr>
            <td>ozp:required</td>
            <td>Listings that the current listing needs to function</td> 
        </tr>
		<tr>
            <td>ozp:required-by</td>
            <td>Listings that need the current listing to function</td> 
        </tr>
		<tr>
            <td>ozp:review</td>
            <td>Reviews associated with the listing</td> 
        </tr>
		<tr>
            <td>self</td>
            <td>This call returns the listing in JSON format</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/listing`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  


##Request Methods

###<a name=PUT>PUT</a>###
Use this call to **update** a listing in the system.

#####Request
######URL
If you want to test the validity of a listing in the system, enter:
`https://localhost:8443/marketplace/api/listing/{id}`
######Heading
    Accept = application/JSON
    Content-Type = application/JSON
######Body

	{  
	    "id":99999,
	    "description":"Description test",
	    "techPocs":[  
	        "techPOC test",
	        "555-555-5555"
	    ],
	    "class":"marketplace.listing",
	    "versionName":"2",
	    "satisfiedScoreCardItems":[  
	
	    ],
	    "isEnabled":true,
	    "installUrl":"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Hulkling-icon.png",
	    "owners":[  
    	    {  
    	        "username":"testAdmin1"
    	    }
    	],
	    "imageLargeUrl":"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Hulkling-Sulking-icon.png",
	    "imageSmallUrl":"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Hulkling-Sulking-icon.png",
	    "launchUrl":"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Hulkling-Sulking-icon.png",
	    "validLaunchUrl":true,
	    "requirements":"Requirements Test",
	    "title":"${titlePos}",
	    "organization":"Test Admin Organization",
	    "releaseDate":"2013-11-15T05:00:00Z",
	    "dependencies":"Requirements test",
	    "type":"Jmeter Type",
	    "isPublished":true,
	    "categories":[  
	        "Jmeter Category"
	    ]
	}

This API tests for invalid listings, expect error responses. 

#####Response Code:
404

#####Response for one type id
	{
    	"error":true,
    	"message":"Listing with id 99999 not found"
	}


###<a name=GET>GET</a>###
Use this call to **read or view** a listing or all the listings in the system.

#####Request
If you want to test the validity of a listing in the system, enter:
`https://localhost:8443/marketplace/api/listing/{id}`
 
This API tests for invalid listings, expect error responses. 

#####Response Code:
404

#####Response for one type id
	{
    	"error":true,
    	"message":"Listing with id 99999 not found"
	}



###<a name=DELETE>DELETE</a>###
Use this call to test removing an invalid listing from the system.
#####Requirements
`https://localhost:8443/marketplace/api/listing/{id}`

#####Response Code:
404

#####Response
	{
    	"error":true,
    	"message":"Listing with id 99999 not found"
	}   
       
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
            <td>404
            <td>This API tests for errors, expect to see them returned for every call.
            <td>If you do not  the listing.</td> 
        </tr> 
    </tbody>
</table> 



