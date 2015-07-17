#API Object: marketplace.metadata

##Definition 
Use the `/metadata` API to read agency, type, intent, category and contact types that appear on Marketplace's Search and Discovery Page. 

 
##Request URL

`https://localhost:8443/marketplace/api/metadata`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Method
The `metadata` API only uses the GET method. 

###<a name=GET>GET</a>###
Use this call to **read or view** all the corresponding metadata used on the Search and Discovery Page.

#####Request
`https://localhost:8443/marketplace/api/metadata/`


#####Response Code:
200

#####Response for metadata

    {
    	"_links": {
    		"curies": {
    			"href": "http://ozoneplatform.org/docs/rels/{rel}",
    			"name": "ozp",
    			"templated": true
    		},
    		"ozp:category": {
    			"href": "https://localhost:8443/marketplace/api/category"
    		},
    		"ozp:contact-type": {
    			"href": "https://localhost:8443/marketplace/api/contactType"
    		},
    		"ozp:intent": {
    			"href": "https://localhost:8443/marketplace/api/intent"
    		},
    		"ozp:organization": {
    			"href": "https://localhost:8443/marketplace/api/agency"
    		},
    		"ozp:type": {
    			"href": "https://localhost:8443/marketplace/api/type"
    		},
    		"self": {
    			"href": "https://localhost:8443/marketplace/api/metadata"
    		}
    	},
    	"_embedded": {
    		"ozp:category": {
    			"_links": {
    				"item": [{
    					"href": "https://localhost:8443/marketplace/api/category/10"
    				},
    				{
    					"href": "https://localhost:8443/marketplace/api/category/9"
    				},
    				{
    					"href": "https://localhost:8443/marketplace/api/category/1"
    				}],
    				"self": {
    					"href": "https://localhost:8443/marketplace/api/category"
    				}
    			},
    			"_embedded": {
    				"item": [{
    					"id": 10,
    					"description": "Books and Reference",
    					"title": "Books and Reference",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/category/10"
    						}
    					}
    				},
    				{
    					"id": 9,
    					"description": "Business",
    					"title": "Business",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/category/9"
    						}
    					}
    				},
    				{
    					"id": 1,
    					"description": "Communication",
    					"title": "Communication",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/category/1"
    						}
    					}
    		 }
    		}],
    		"ozp:contact-type": {
    			"_links": {
    				"item": [{
    					"href": "https://localhost:8443/marketplace/api/contactType/30"
    				},
    				{
    					"href": "https://localhost:8443/marketplace/api/contactType/31"
    				}],
    				"self": {
    					"href": "https://localhost:8443/marketplace/api/contactType"
    				}
    			},
    			"_embedded": {
    				"item": [{
    					"id": 30,
    					"required": true,
    					"title": "ContactType_8",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/contactType/30"
    						}
    					}
    				},
    				{
    					"id": 31,
    					"required": true,
    					"title": "ContactType_9",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/contactType/31"
    						}
    					}
    				}],
    			}
    		},
    		"ozp:intent": {
    			"_links": {
    				"item": [{
    					"href": "https://localhost:8443/marketplace/api/intent/48"
    				},
    				{
    					"href": "https://localhost:8443/marketplace/api/intent/50"
    				}],
    				"self": {
    					"href": "https://localhost:8443/marketplace/api/intent"
    				}
    			},
    			"_embedded": {
    				"item": [{
    					"id": 48,
    					"type": "application10/json",
    					"action": "view10",
    					"label": "label10",
    					"iconId": null,
    					"icon": null,
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/intent/48"
    						}
    					}
    				},
    				{
    					"id": 50,
    					"type": "application/json",
    					"action": "share",
    					"label": null,
    					"iconId": null,
    					"icon": null,
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/intent/50"
    						}
    					}
    				}]
    			}
    		},
    		"ozp:organization": {
    			"_links": {
    				"item": [{
    					"href": "https://localhost:8443/marketplace/api/agency/1"
    				},
    				{
    					"href": "https://localhost:8443/marketplace/api/agency/2"
    				},
    				{
    					"href": "https://localhost:8443/marketplace/api/agency/3"
    				}],
    				"self": {
    					"href": "https://localhost:8443/marketplace/api/agency"
    				}
    			},
    			"_embedded": {
    				"item": [{
    					"id": 1,
    					"shortName": "TORG",
    					"title": "Test Organization",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/agency/1"
    						}
    					}
    				},
    				{
    					"id": 2,
    					"shortName": "TORG2",
    					"title": "Test 2 Organization",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/agency/2"
    						}
    					}
    				},
    				{
    					"id": 3,
    					"shortName": "JAgency",
    					"title": "Jmeter Agency",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/agency/3"
    						}
    					}
    				}]
    			}
    		},
    		"ozp:type": {
    			"_links": {
    				"item": [{
    					"href": "https://localhost:8443/marketplace/api/type/1"
    				},
    				{
    					"href": "https://localhost:8443/marketplace/api/type/2"
    				},
    				{
    					"href": "https://localhost:8443/marketplace/api/type/3"
    				}],
    				"self": {
    					"href": "https://localhost:8443/marketplace/api/type"
    				}
    			},
    			"_embedded": {
    				"item": [{
    					"id": 1,
    					"description": "A small or highly specialized application",
    					"title": "Widget",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/type/1"
    						}
    					}
    				},
    				{
    					"id": 2,
    					"description": "A web app",
    					"title": "Web Application",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/type/2"
    						}
    					}
    				},
    				{
    					"id": 3,
    					"description": "This is a type that is used for Jmeter testing. It should be removed at the end of a test.",
    					"title": "Jmeter Type",
    					"_links": {
    						"self": {
    							"href": "https://localhost:8443/marketplace/api/type/3"
    						}
    					}
    				}]
    			}
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
            <td>The metadata API only GETs data. You cannot create, update or delete it.</td> 
        </tr>
    </tbody>
</table> 



