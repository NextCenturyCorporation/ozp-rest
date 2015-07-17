#API Object: marketplace.storefront

##Definition 
Use the `/storefront` API to read all the listing data on Marketplace's Search and Discovery Page. 

##Request URL

`https://localhost:8443/marketplace/api/storefront`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Method
The `storefront` API only uses the GET method. 

###<a name=GET>GET</a>###
Use this call to **read or view** all the listing data that appears on the Search and Discovery Page.

#####Request
`https://localhost:8443/marketplace/api/storefront/`


#####Response Code:
200

#####Response for storefront

    {
    	"mostPopular": [{
    		"id": 1,
    		"type": "Web Application",
    		"smallIconId": "a6e3316c-bef3-4bad-a496-b1fa6f63fee3",
    		"description": "The clipboard is a software facility used for short-term data storage and/or data transfer between documents or applications, via copy and paste operations. It is most commonly a part of a GUI environment and is usually implemented as an anonymous, temporary data buffer that can be accessed from most or all programs within the environment via defined programming interfaces. A typical application accesses clipboard functionality by mapping user input (keybindings, menu selections, etc.) to these interfaces.",
    		"singleton": false,
    		"height": null,
    		"width": null,
    		"required": [],
    		"title": "Clipboard",
    		"largeIconId": "f7ded6ee-61bd-4126-a91b-55ca8b8e2eed",
    		"bannerIconId": "a7c695c2-0aff-42af-a182-717abe9d7368",
    		"featuredBannerIconId": "796e8d11-eb75-432a-ad89-db7a5f3c9acc",
    		"descriptionShort": "Clipboard managers are applications that enable user to manipulate clipboard.",
    		"requirements": "https://www",
    		"approvalStatus": "APPROVED",
    		"totalComments": 0,
    		"screenshots": [{
    			"smallImageId": "ee34591b-c303-41c1-a2b0-c73433597ac9",
    			"largeImageId": "604b4b09-56e5-43a5-bfea-fddd1ccf1121",
    			"smallImageUrl": "https://localhost:8443/marketplace/api/image/ee34591b-c303-41c1-a2b0-c73433597ac9.png",
    			"largeImageUrl": "https://localhost:8443/marketplace/api/image/604b4b09-56e5-43a5-bfea-fddd1ccf1121.png"
    		},
    		{
    			"smallImageId": "ebd179d9-b526-43fd-b886-be0e442ade87",
    			"largeImageId": "d6bff664-502d-4f64-bbed-4d091a44afe4",
    			"smallImageUrl": "https://localhost:8443/marketplace/api/image/ebd179d9-b526-43fd-b886-be0e442ade87.png",
    			"largeImageUrl": "https://localhost:8443/marketplace/api/image/d6bff664-502d-4f64-bbed-4d091a44afe4.png"
    		}],
    		"editedDate": "2015-07-14T18:41:32.208+0000",
    		"approvedDate": "2015-07-14T18:41:32.173+0000",
    		"versionName": "1",
    		"launchUrl": "https://raw.githubusercontent.com/ozone-development/center-ui/master/app/images/sample-listings/Clipboard.png",
    		"uuid": "a7c57f89-8b86-4c43-97e0-39f659c7a069",
    		"isEnabled": true,
    		"whatIsNew": "Added languages: Arabic, Japanese, Chinese, German, French, Korean, Russian and Spanish",
    		"isFeatured": false,
    		"avgRate": 0.0,
    		"totalVotes": 0,
    		"totalRate5": 0,
    		"totalRate4": 0,
    		"totalRate3": 0,
    		"totalRate2": 0,
    		"totalRate1": 0,
    		"contacts": [],
    		"agency": "Test 2 Organization",
    		"categories": ["Media and Video",
    		"Tools"],
    		"owners": [{
    			"id": 2,
    			"displayName": "Test Admin 1",
    			"username": "testAdmin1"
    		}],
    		"docUrls": [{
    			"name": "API Documentation",
    			"url": "http://www.yahoo.com"
    		},
    		{
    			"name": "User Manual",
    			"url": "http://www.google.com"
    		}],
    		"intents": ["application/ozp-demo-ball+json/view",
    		"application/ozp-demo-ball+json/edit"],
    		"tags": ["blue"],
    		"imageSmallUrl": "https://localhost:8443/marketplace/api/image/a6e3316c-bef3-4bad-a496-b1fa6f63fee3.png",
    		"imageMediumUrl": "https://localhost:8443/marketplace/api/image/f7ded6ee-61bd-4126-a91b-55ca8b8e2eed.png",
    		"imageLargeUrl": "https://localhost:8443/marketplace/api/image/a7c695c2-0aff-42af-a182-717abe9d7368.png",
    		"imageXlargeUrl": "https://localhost:8443/marketplace/api/image/796e8d11-eb75-432a-ad89-db7a5f3c9acc.png",
    		"agencyShort": "TORG2",
    		"currentRejection": null,
    		"_links": {
    			"curies": {
    				"href": "http://ozoneplatform.org/docs/rels/{rel}",
    				"name": "ozp",
    				"templated": true
    			},
    			"ozp:activity": {
    				"href": "https://localhost:8443/marketplace/api/listing/1/activity"
    			},
    			"ozp:required": {
    				"href": "https://localhost:8443/marketplace/api/listing/1/requiredListings"
    			},
    			"ozp:required-by": {
    				"href": "https://localhost:8443/marketplace/api/listing/1/requiringListings"
    			},
    			"ozp:review": {
    				"href": "https://localhost:8443/marketplace/api/listing/1/itemComment"
    			},
    			"self": {
    				"href": "https://localhost:8443/marketplace/api/listing/1"
    			}
    		}
    	}],
    	"featured": [],
    	"recent": [{
    		"id": 1,
    		"type": "Web Application",
    		"smallIconId": "a6e3316c-bef3-4bad-a496-b1fa6f63fee3",
    		"description": "The clipboard is a software facility used for short-term data storage and/or data transfer between documents or applications, via copy and paste operations. It is most commonly a part of a GUI environment and is usually implemented as an anonymous, temporary data buffer that can be accessed from most or all programs within the environment via defined programming interfaces. A typical application accesses clipboard functionality by mapping user input (keybindings, menu selections, etc.) to these interfaces.",
    		"singleton": false,
    		"height": null,
    		"width": null,
    		"required": [],
    		"title": "Clipboard",
    		"largeIconId": "f7ded6ee-61bd-4126-a91b-55ca8b8e2eed",
    		"bannerIconId": "a7c695c2-0aff-42af-a182-717abe9d7368",
    		"featuredBannerIconId": "796e8d11-eb75-432a-ad89-db7a5f3c9acc",
    		"descriptionShort": "Clipboard managers are applications that enable user to manipulate clipboard.",
    		"requirements": "https://www",
    		"approvalStatus": "APPROVED",
    		"totalComments": 0,
    		"screenshots": [{
    			"smallImageId": "ee34591b-c303-41c1-a2b0-c73433597ac9",
    			"largeImageId": "604b4b09-56e5-43a5-bfea-fddd1ccf1121",
    			"smallImageUrl": "https://localhost:8443/marketplace/api/image/ee34591b-c303-41c1-a2b0-c73433597ac9.png",
    			"largeImageUrl": "https://localhost:8443/marketplace/api/image/604b4b09-56e5-43a5-bfea-fddd1ccf1121.png"
    		},
    		{
    			"smallImageId": "ebd179d9-b526-43fd-b886-be0e442ade87",
    			"largeImageId": "d6bff664-502d-4f64-bbed-4d091a44afe4",
    			"smallImageUrl": "https://localhost:8443/marketplace/api/image/ebd179d9-b526-43fd-b886-be0e442ade87.png",
    			"largeImageUrl": "https://localhost:8443/marketplace/api/image/d6bff664-502d-4f64-bbed-4d091a44afe4.png"
    		}],
    		"editedDate": "2015-07-14T18:41:32.208+0000",
    		"approvedDate": "2015-07-14T18:41:32.173+0000",
    		"versionName": "1",
    		"launchUrl": "https://raw.githubusercontent.com/ozone-development/center-ui/master/app/images/sample-listings/Clipboard.png",
    		"uuid": "a7c57f89-8b86-4c43-97e0-39f659c7a069",
    		"isEnabled": true,
    		"whatIsNew": "Added languages: Arabic, Japanese, Chinese, German, French, Korean, Russian and Spanish",
    		"isFeatured": false,
    		"avgRate": 0.0,
    		"totalVotes": 0,
    		"totalRate5": 0,
    		"totalRate4": 0,
    		"totalRate3": 0,
    		"totalRate2": 0,
    		"totalRate1": 0,
    		"contacts": [],
    		"agency": "Test 2 Organization",
    		"categories": ["Media and Video",
    		"Tools"],
    		"owners": [{
    			"id": 2,
    			"displayName": "Test Admin 1",
    			"username": "testAdmin1"
    		}],
    		"docUrls": [{
    			"name": "API Documentation",
    			"url": "http://www.yahoo.com"
    		},
    		{
    			"name": "User Manual",
    			"url": "http://www.google.com"
    		}],
    		"intents": ["application/ozp-demo-ball+json/view",
    		"application/ozp-demo-ball+json/edit"],
    		"tags": ["blue"],
    		"imageSmallUrl": "https://localhost:8443/marketplace/api/image/a6e3316c-bef3-4bad-a496-b1fa6f63fee3.png",
    		"imageMediumUrl": "https://localhost:8443/marketplace/api/image/f7ded6ee-61bd-4126-a91b-55ca8b8e2eed.png",
    		"imageLargeUrl": "https://localhost:8443/marketplace/api/image/a7c695c2-0aff-42af-a182-717abe9d7368.png",
    		"imageXlargeUrl": "https://localhost:8443/marketplace/api/image/796e8d11-eb75-432a-ad89-db7a5f3c9acc.png",
    		"agencyShort": "TORG2",
    		"currentRejection": null,
    		"_links": {
    			"curies": {
    				"href": "http://ozoneplatform.org/docs/rels/{rel}",
    				"name": "ozp",
    				"templated": true
    			},
    			"ozp:activity": {
    				"href": "https://localhost:8443/marketplace/api/listing/1/activity"
    			},
    			"ozp:required": {
    				"href": "https://localhost:8443/marketplace/api/listing/1/requiredListings"
    			},
    			"ozp:required-by": {
    				"href": "https://localhost:8443/marketplace/api/listing/1/requiringListings"
    			},
    			"ozp:review": {
    				"href": "https://localhost:8443/marketplace/api/listing/1/itemComment"
    			},
    			"self": {
    				"href": "https://localhost:8443/marketplace/api/listing/1"
    			}
    		}
    	}],
    	"_links": {
    		"self": {
    			"href": "https://localhost:8443/marketplace/api/storefront"
    		}
    	}
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
            <td>400
            <td>Create or Delete
            <td>The storefront API only GETs data. You cannot create, update or delete it.</td> 
        </tr>
    </tbody>
</table> 



