#API Object: marketplace.Listing_Create

##Definition 
Use the `/Listing_Create` API to create, update, read or delete a listing in the system. 

##Request Method TOC
Each method will be explained in their respective section.
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_listing_createAPI.md#POST),
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_listing_createAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_listing_createAPI.md#DELETE)
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

###<a name=POST>POST</a>
Use this call to **create** a listing in the system.

#####Request
######URL
`https://localhost:8443/marketplace/api/listing`

#######Heading
    Accept = application/JSON
    Content-Type = application/JSON



#####Response Code:
201

#####Response

	{
	  "id": 417,
	  "type": "Web Application",
	  "description": "A chart is a graphical representation of data, in which the data is represented by symbols, such as bars in a bar chart...might be best shown as a line chart.",
	  "singleton": false,
	  "imageSmallUrl": "https://localhost:8443/marketplace/api/image/a84db4e7-4327-43b7-acf9-02efbec8b22b.png",
	  "imageMediumUrl": "https://localhost:8443/marketplace/api/image/6140ecb2-a5c1-4477-9bc7-2ef3770655b7.png",
	  "imageLargeUrl": "https://localhost:8443/marketplace/api/image/2a836730-092c-4151-8dea-0b3b3c973573.png",
	  "imageXlargeUrl": "https://localhost:8443/marketplace/api/image/ebc10d98-da35-41d1-9201-b6669f3421ac.png",
	  "agencyShort": "TORG",
	  "currentRejection": null,
	  "required": [],
	  "tags": [
	    "blue"
	  ],
	  "height": null,
	  "width": null,
	  "launchUrl": "https://raw.githubusercontent.com/ozone-development/center-ui/master/app/images/sample-listings/ChartCourse.png",
	  "uuid": "cc275d03-8dd3-4019-996a-b349d9de753c",
	  "whatIsNew": "Bug fixes",
	  "isEnabled": true,
	  "isFeatured": false,
	  "avgRate": 0,
	  "totalVotes": 0,
	  "totalRate5": 0,
	  "totalRate4": 0,
	  "totalRate3": 0,
	  "totalRate2": 0,
	  "totalRate1": 0,
	  "contacts": [],
	  "agency": "Test Organization",
	  "categories": [
	    "Sports",
	    "Health and Fitness",
	    "Productivity",
	    "Tools",
	    "Education"
	  ],
	  "owners": [
	    {
	      "id": 2,
	      "displayName": "Test Admin 1",
	      "username": "testAdmin1"
	    }
	  ],
	  "docUrls": [
	    {
	      "name": "API Documentation",
	      "url": "http://www.yahoo.com"
	    },
	    {
	      "name": "User Manual",
	      "url": "http://www.google.com"
	    }
	  ],
	  "intents": [],
	  "title": "Chart Course",
	  "approvedDate": "2015-08-03T14:45:54.454+0000",
	  "versionName": "3.1",
	  "smallIconId": "a84db4e7-4327-43b7-acf9-02efbec8b22b",
	  "largeIconId": "6140ecb2-a5c1-4477-9bc7-2ef3770655b7",
	  "bannerIconId": "2a836730-092c-4151-8dea-0b3b3c973573",
	  "featuredBannerIconId": "ebc10d98-da35-41d1-9201-b6669f3421ac",
	  "descriptionShort": "A chart can take a large variety of forms with its ability to extract meaning from data.",
	  "requirements": "https://www",
	  "approvalStatus": "APPROVED",
	  "totalComments": 0,
	  "screenshots": [
	    {
	      "largeImageUrl": "https://localhost:8443/marketplace/api/image/04731196-e5fb-42c3-86dc-2305147228fc.png",
	      "smallImageUrl": "https://localhost:8443/marketplace/api/image/5aa628af-0934-4c93-ab96-ef0a88b8961f.png",
	      "smallImageId": "5aa628af-0934-4c93-ab96-ef0a88b8961f",
	      "largeImageId": "04731196-e5fb-42c3-86dc-2305147228fc"
	    },
	    {
	      "largeImageUrl": "https://localhost:8443/marketplace/api/image/afb05299-d0c8-4880-8af6-8b4002714133.png",
	      "smallImageUrl": "https://localhost:8443/marketplace/api/image/78ee4b01-c64c-405c-8595-f317fe39121b.png",
	      "smallImageId": "78ee4b01-c64c-405c-8595-f317fe39121b",
	      "largeImageId": "afb05299-d0c8-4880-8af6-8b4002714133"
	    }
	  ],
	  "editedDate": "2015-08-03T14:45:54.467+0000",
	  "_links": {
	    "curies": {
	      "href": "http://ozoneplatform.org/docs/rels/{rel}",
	      "name": "ozp",
	      "templated": true
	    },
	    "ozp:activity": {
	      "href": "https://localhost:8443/marketplace/api/listing/417/activity"
	    },
	    "ozp:required": {
	      "href": "https://localhost:8443/marketplace/api/listing/417/requiredListings"
	    },
	    "ozp:required-by": {
	      "href": "https://localhost:8443/marketplace/api/listing/417/requiringListings"
	    },
	    "ozp:review": {
	      "href": "https://localhost:8443/marketplace/api/listing/417/itemComment"
	    },
	    "self": {
	      "href": "https://localhost:8443/marketplace/api/listing/417"
	    }
	  }
	}

#####Requirements
To save a listing, it must include a name and type.  
<br>


###<a name=GET>GET</a>###
Use this call to **read or view** a listing or all the listings in the system.

#####Request
If you want to see a list of all the listings in the system, enter:
`https://localhost:8443/marketplace/api/listing/`

However, to view metadata about only one listing, enter:
`https://localhost:8443/marketplace/api/listing/{id}`
 
Marketplace (i.e., Center) returns the representation of the listing that matches the{id}, as shown in the Response for one listing id. 

#####Response Code:
200

#####Response for one type id
	{
	  "id": 417,
	  "type": "Web Application",
	  "description": "A chart is a graphical representation of data, in which the data is represented by symbols, such as bars in a bar chart...might be best shown as a line chart.",
	  "singleton": false,
	  "imageSmallUrl": "https://localhost:8443/marketplace/api/image/a84db4e7-4327-43b7-acf9-02efbec8b22b.png",
	  "imageMediumUrl": "https://localhost:8443/marketplace/api/image/6140ecb2-a5c1-4477-9bc7-2ef3770655b7.png",
	  "imageLargeUrl": "https://localhost:8443/marketplace/api/image/2a836730-092c-4151-8dea-0b3b3c973573.png",
	  "imageXlargeUrl": "https://localhost:8443/marketplace/api/image/ebc10d98-da35-41d1-9201-b6669f3421ac.png",
	  "agencyShort": "TORG",
	  "currentRejection": null,
	  "required": [],
	  "tags": [
	    "blue"
	  ],
	  "height": null,
	  "width": null,
	  "launchUrl": "https://raw.githubusercontent.com/ozone-development/center-ui/master/app/images/sample-listings/ChartCourse.png",
	  "uuid": "cc275d03-8dd3-4019-996a-b349d9de753c",
	  "whatIsNew": "Bug fixes",
	  "isEnabled": true,
	  "isFeatured": false,
	  "avgRate": 0,
	  "totalVotes": 0,
	  "totalRate5": 0,
	  "totalRate4": 0,
	  "totalRate3": 0,
	  "totalRate2": 0,
	  "totalRate1": 0,
	  "contacts": [],
	  "agency": "Test Organization",
	  "categories": [
	    "Sports",
	    "Health and Fitness",
	    "Productivity",
	    "Tools",
	    "Education"
	  ],
	  "owners": [
	    {
	      "id": 2,
	      "displayName": "Test Admin 1",
	      "username": "testAdmin1"
	    }
	  ],
	  "docUrls": [
	    {
	      "name": "API Documentation",
	      "url": "http://www.yahoo.com"
	    },
	    {
	      "name": "User Manual",
	      "url": "http://www.google.com"
	    }
	  ],
	  "intents": [],
	  "title": "Chart Course",
	  "approvedDate": "2015-08-03T14:45:54.454+0000",
	  "versionName": "3.1",
	  "smallIconId": "a84db4e7-4327-43b7-acf9-02efbec8b22b",
	  "largeIconId": "6140ecb2-a5c1-4477-9bc7-2ef3770655b7",
	  "bannerIconId": "2a836730-092c-4151-8dea-0b3b3c973573",
	  "featuredBannerIconId": "ebc10d98-da35-41d1-9201-b6669f3421ac",
	  "descriptionShort": "A chart can take a large variety of forms with its ability to extract meaning from data.",
	  "requirements": "https://www",
	  "approvalStatus": "APPROVED",
	  "totalComments": 0,
	  "screenshots": [
	    {
	      "largeImageUrl": "https://localhost:8443/marketplace/api/image/04731196-e5fb-42c3-86dc-2305147228fc.png",
	      "smallImageUrl": "https://localhost:8443/marketplace/api/image/5aa628af-0934-4c93-ab96-ef0a88b8961f.png",
	      "smallImageId": "5aa628af-0934-4c93-ab96-ef0a88b8961f",
	      "largeImageId": "04731196-e5fb-42c3-86dc-2305147228fc"
	    },
	    {
	      "largeImageUrl": "https://localhost:8443/marketplace/api/image/afb05299-d0c8-4880-8af6-8b4002714133.png",
	      "smallImageUrl": "https://localhost:8443/marketplace/api/image/78ee4b01-c64c-405c-8595-f317fe39121b.png",
	      "smallImageId": "78ee4b01-c64c-405c-8595-f317fe39121b",
	      "largeImageId": "afb05299-d0c8-4880-8af6-8b4002714133"
	    }
	  ],
	  "editedDate": "2015-08-03T14:45:54.467+0000",
	  "_links": {
	    "curies": {
	      "href": "http://ozoneplatform.org/docs/rels/{rel}",
	      "name": "ozp",
	      "templated": true
	    },
	    "ozp:activity": {
	      "href": "https://localhost:8443/marketplace/api/listing/417/activity"
	    },
	    "ozp:required": {
	      "href": "https://localhost:8443/marketplace/api/listing/417/requiredListings"
	    },
	    "ozp:required-by": {
	      "href": "https://localhost:8443/marketplace/api/listing/417/requiringListings"
	    },
	    "ozp:review": {
	      "href": "https://localhost:8443/marketplace/api/listing/417/itemComment"
	    },
	    "self": {
	      "href": "https://localhost:8443/marketplace/api/listing/417"
	    }
	  }
	}


#####Requirements
Listings must contain a name and type. 

###<a name=DELETE>DELETE</a>###
Use this call to remove a listing from the system.
#####Requirements
`https://localhost:8443/marketplace/api/listing/{id}`

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
            <td>
            <td>Listings will not save without a name and type.
            <td>Enter a name and type for the listing.</td> 
        </tr>
        <tr>
            <td>
            <td>URLs must be secure.
            <td>URLs associated with the listing (it's address, icons, screenshots) must use https addresses.</td> 
        </tr>  
    </tbody>
</table> 



