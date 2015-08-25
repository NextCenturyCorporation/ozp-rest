### 1.0.4 (2015-08-25)


#### Bug Fixes

* **LegacyWidgetCollectionRepresentation:** remove double wrapping in Response ([649630e9](https://github.com/ozone-development/ozp-rest/commit/649630e906d86a69e5b0ffc1cd26a5838d464905))


### 1.0.3 (2015-08-18)


#### Bug Fixes

* **LegacyResource:** Change format of LegacyResource response ([e9ee53e1](https://github.com/ozone-development/ozp-rest/commit/e9ee53e18feb2ded4d07ac803812fd7d4c18f81b))


#### Features

* **navbar:** Add counts-only endpoint ([304cc891](https://github.com/ozone-development/ozp-rest/commit/304cc8910b0a2ca7323c29bc0df337db9570f5a6))


### 1.0.2 (2015-08-11)


### 1.0.1 (2015-07-22)


#### Bug Fixes

* **Authorization:** add case-insensitive compare for usernames ([9cc7fd5a](https://github.com/ozone-development/ozp-rest/commit/9cc7fd5a3b13151fc30929c83b13a33ba39bd1a9))


## 1.0.0 (2015-07-22)


### 0.7.16 (2015-07-14)


### 0.7.15 (2015-06-30)


#### Features

* **Sample Data:** update sample data to include new widgets ([91a0c4e9](https://github.com/ozone-development/ozp-rest/commit/91a0c4e9f5c1995ab99d374a4d01f0a449580781))


### 0.7.14 (2015-06-25)


### 0.7.13 (2015-06-22)


#### Bug Fixes

* **Categories:** Alphabetize categories #81 ([dd700c70](https://github.com/ozone-development/ozp-rest/commit/dd700c70909dd947c4616c5a356e862592276465))


### 0.7.12 (2015-06-09)


#### Bug Fixes

* **Logfiles:** Check needed to avoid warning messages for symbolic links ([c4c5db79](https://github.com/ozone-development/ozp-rest/commit/c4c5db79162d50d023fb7ddbd9aef624b0170752))



### 0.7.11 (2015-06-02)


#### Bug Fixes

* **Contacts:** accept apostrophes in email addresses #76 ([8c06488d](https://github.com/ozone-development/ozp-rest/commit/8c06488d26d766d46e829d0b8f7f2f2f4f0ea8aa))


### 0.7.10 (2015-05-26)


#### Bug Fixes

* **Legacy:**
  * syntax error ([e874e371](https://github.com/ozone-development/ozp-rest/commit/e874e371ab9341387b6a865d91cb80b6651f1c2d))
  * updated  for universal name search ([b938723d](https://github.com/ozone-development/ozp-rest/commit/b938723db56733d8c9bc050fbce7ece7b2474b0f))


#### Features

* **Legacy:**
  * Changed windownamePrelude in AbstractWindownameWriter.java ([87ebb9ab](https://github.com/ozone-development/ozp-rest/commit/87ebb9ab0d5cfbfb2b12031e8573e9a84e60845d))
  * Fixed error in LegacyWidgetCollectionRepresentation.groovy ([c3d150e0](https://github.com/ozone-development/ozp-rest/commit/c3d150e0077ce1f1302123e001dae8ab2268a716))
  * Fixed a small bug in LegacyPreference.groovy ([c9a9d07d](https://github.com/ozone-development/ozp-rest/commit/c9a9d07d8161bdea36c8c6297e76e299cf04e82a))
  * Corrected return values of some functions in Legacy Resource. ([a0941dc9](https://github.com/ozone-development/ozp-rest/commit/a0941dc9eec4ca7427b3d0fea110e6b915e27949))
  * updated windowname writer to add  etc. ([9a6930f1](https://github.com/ozone-development/ozp-rest/commit/9a6930f1fe128676af82ea4257e0aa755577eb2e))
  * formatted results for collections of widgets ([58eecca6](https://github.com/ozone-development/ozp-rest/commit/58eecca677fe24c64718e51d2ae3e1cbe2281ac4))
  * Corrected return type of getPreference in LegacyResource.groovy ([fd2de37e](https://github.com/ozone-development/ozp-rest/commit/fd2de37e24cedd313435a146b0c466d24681a0be))
  * add support for universal name for listUserAndGroupWidgets, syntax error ([992057c6](https://github.com/ozone-development/ozp-rest/commit/992057c6fc1d78b47525128be68c0d7d33e6d68a))
  * update preferences to pull collection ([e1228bd8](https://github.com/ozone-development/ozp-rest/commit/e1228bd80f3ddf200943aac3a03e6b09414ca1a0))
  * Added functionality for getPreference to return a collection of all matching pre ([10ce37f5](https://github.com/ozone-development/ozp-rest/commit/10ce37f5ff5b6aedc171aa12a10295040b9fe312))
  * Corrected the formatting of data between LegacyHTTPMethodServletFilter and Legac ([6edb8c98](https://github.com/ozone-development/ozp-rest/commit/6edb8c98a0c88ed4e63696cb92d5770a567e92f3))
  * Modified the attributes of LegacyWidget to correctly match up to OWF widget attr ([b4671529](https://github.com/ozone-development/ozp-rest/commit/b4671529e2e73a5ce9f4dad1d1962c9f280c206e))
  *  and  done #44 #41 ([ec4cde36](https://github.com/ozone-development/ozp-rest/commit/ec4cde36d7c912a7c250438989924b9467ca1cb2))
  * getWidget by guid working #43 ([d3fa70a9](https://github.com/ozone-development/ozp-rest/commit/d3fa70a9f9c0abde071a0f3e4c19d3b0b346684a))


### 0.7.9 (2015-05-19)


#### Bug Fixes

* **Legacy:** fix windowname writer issue for  calls ([48e3b9d3](https://github.com/ozone-development/ozp-rest/commit/48e3b9d39d3b7052cb73bb3c4f1a2864272fe587))


### 0.7.8 (2015-05-12)


#### Bug Fixes

* **Dependencies:** #66 no longer depend on owfgoss.org infrastructure ([cdf56477](https://github.com/ozone-development/ozp-rest/commit/cdf56477cc88c1fa5128cd2edafab3cd834465a0))


#### Features

* **Legacy:**
  * #43 working findWidgets with or without sql wildcards ([e171aa5d](https://github.com/ozone-development/ozp-rest/commit/e171aa5de8f4368fac02a0778d9d1a3787dac0ff))
  * findWidgets with config objects working #43 ([5edd79a4](https://github.com/ozone-development/ozp-rest/commit/5edd79a42dff13e70c3542f52f3d9f00022d1d6d))
  *  working version ([a4974869](https://github.com/ozone-development/ozp-rest/commit/a49748692d2d7f3517667b41882aa1c5b7db836b))
  * windowname writer for user preference, code cleanup ([b627cdb6](https://github.com/ozone-development/ozp-rest/commit/b627cdb6b34153870eac7b30d9e144336dc58e9f))
  * get current user ([8c6ffa4c](https://github.com/ozone-development/ozp-rest/commit/8c6ffa4cf8d8e8f00c1f3784b7b3d0ffed47d0ee))
  * update to correctly formatted JSON ([15a022e2](https://github.com/ozone-development/ozp-rest/commit/15a022e2eee31ea041c95bfcb43c075c62be6b20))
  * update json for legacy calls ([13e6d08b](https://github.com/ozone-development/ozp-rest/commit/13e6d08b4f1f12843a15ae9e9f7f9d23f988810d))
  * delete a user preference ([7b6e855d](https://github.com/ozone-development/ozp-rest/commit/7b6e855dc8e3efd34a64dc9063daf1aa944869ca))
  * getting user preferences ([01c734c3](https://github.com/ozone-development/ozp-rest/commit/01c734c345be086d829fec9f6fbedf48317673ac))
  * working form urlencoding ([9c0a1a46](https://github.com/ozone-development/ozp-rest/commit/9c0a1a468cc5cf7bb9fda461cde1e3629224af85))
  * working ([784aa1f9](https://github.com/ozone-development/ozp-rest/commit/784aa1f95eeaaaf434c797d8171140a9147be5c6))
  * first round of work on setting user preferences ([dee07089](https://github.com/ozone-development/ozp-rest/commit/dee070893a846da9be6006e0bba106df2f42c108))
  * Windowname transport code improvements ([91922ef3](https://github.com/ozone-development/ozp-rest/commit/91922ef31ffb1b7fc29ddaf5ea7504c5922c992e))
  * windowname response writer ([3309c456](https://github.com/ozone-development/ozp-rest/commit/3309c4564cb96ba0705ea1be2ad720ab44f5004b))
  * Switch to servlet filter for _method handling ([17cf5e3e](https://github.com/ozone-development/ozp-rest/commit/17cf5e3eb29019d883802f96d5838bc7761b344a))
* **legacy:** Initial code for windowname transport ([3401122f](https://github.com/ozone-development/ozp-rest/commit/3401122f936c7e88ecae691a81c06315abda4bf6))


### 0.7.7 (2015-05-05)


### 0.7.6 (2015-05-05)


### 0.7.5 (2015-04-28)


#### Bug Fixes

* **Screenshot:** #205 in center, fix validation of screen shots to only apply if the listing is b ([8e9dd853](https://github.com/ozone-development/ozp-rest/commit/8e9dd8534684fc66dbb7b5b70cc2e290578f8e97))


### 0.7.4 (2015-04-14)


#### Bug Fixes

* **Image Server:** imageUriBaseOverride paths should match image directory structure #58 ([5c5d1599](https://github.com/ozone-development/ozp-rest/commit/5c5d159942a7f56ca28b84c02edb1469e4712bc3))


### 0.7.3 (2015-03-31)


#### Features

* **REST efficiency:**
  * #45 Create efficient REST calls for UI load ([20ce34a5](https://github.com/ozone-development/ozp-rest/commit/20ce34a52809459ae796445f9e67b50756d414db))
  * #45 use db instead of elasticsearch for storefront ([ad0ebfe4](https://github.com/ozone-development/ozp-rest/commit/ad0ebfe40b6b383bafd0995165024220057cc83b))
  * #45 consolidate search calls for initial UI page ([9ecfac29](https://github.com/ozone-development/ozp-rest/commit/9ecfac2935de9f73bb8006d118436cdee00c3afa))
* **REST metadata:** center-ui #45 Create a single REST call for retrieving categories, types, etc ([76ff8148](https://github.com/ozone-development/ozp-rest/commit/76ff814898aaa118293ceb08141649d311e3e466))
* **Search:** Combine Type and Agency filters via UNION ([3cddb173](https://github.com/ozone-development/ozp-rest/commit/3cddb173d6a946b1405ccaab7f8492230f855582))


### 0.7.2 (2015-03-23)


#### Bug Fixes

* **Notifications:** fix next link for /notification/expired ([ddd2a8ba](https://github.com/ozone-development/ozp-rest/commit/ddd2a8ba2e82037701d2ea546c4c13dc18192ffb))
* **search:** remove notifications from elasticsearch index ([7e7888a4](https://github.com/ozone-development/ozp-rest/commit/7e7888a430ea297ba9fc8f0a9fe978247456f06d))
* **styling:** added new graphics for demo apps ([89239fad](https://github.com/ozone-development/ozp-rest/commit/89239fad4fa71a13758dcc0e675de3c7a5ba2980))


#### Features

* **Profile Search:**
  * Search for profiles by username ([cf0838a6](https://github.com/ozone-development/ozp-rest/commit/cf0838a687e560ae477e98dc71afd9ec7000cf5c))
  * Switch to search by username ([78cd56aa](https://github.com/ozone-development/ozp-rest/commit/78cd56aa43352a6ad08fb5df3599840d48d07228))
  * query param for finding profiles starting with a string ([20cc884e](https://github.com/ozone-development/ozp-rest/commit/20cc884e7b6371a963243b14f3740e57bf545ea9))


### 0.7.1 (2015-02-18)


## 0.7.0 (2015-02-18)


#### Bug Fixes

* **Change Log:**
  * OZF-975: only return icon if one is present ([2c317036](https://github.com/ozone-development/ozp-rest/commit/2c31703699fb5ff1a1f44b411d292e839c94b550))
  * OZF-911: rejected change logs no longer error out ([d3c04b63](https://github.com/ozone-development/ozp-rest/commit/d3c04b638f2e217ca3e31efe825376fb5d441edb))
* **ItemCommentListingRepresentation:** GET /profile/{id}/itemComment is not returning all itemComments ([c5ed1738](https://github.com/ozone-development/ozp-rest/commit/c5ed1738d91b450b8fef1c12714c1b5887c08d1c))
* **Sample Data:** change descriptionShort to less than 100 chars ([98113342](https://github.com/ozone-development/ozp-rest/commit/98113342b4e1548a74e564d05b5416caa2b724e3))
* **Short Description:** set max size to 100 ([d6b71217](https://github.com/ozone-development/ozp-rest/commit/d6b712177f84d7276eca0e37114c1fd11e939e87))


#### Features

* **Adds system notifications** ([aee174e4](https://github.com/ozone-development/ozp-rest/commit/aee174e421ffc85c5e3aee919f5cef6e27027126))
* **Launch Preference:**
  * OZF-966 Launch Preference ([5eee6907](https://github.com/ozone-development/ozp-rest/commit/5eee690789ac856c9d98b0713340483cb67d6132))
  * OZF-966 launchInWebtop preference ([afd4526b](https://github.com/ozone-development/ozp-rest/commit/afd4526bc678ac15c2cec8d82a650b09477041e6))
* **Recent Activity:**
  * OZF-567: updated service to add user listings to org steward call ([edccec36](https://github.com/ozone-development/ozp-rest/commit/edccec36ca25b133f4359fca76ce54b38f2a2bfd))
  * OZF-567: updated service to return filtered listing activities and updated unit  ([de226432](https://github.com/ozone-development/ozp-rest/commit/de22643206982bb58a3f94580c5d81158f917271))


### 0.5.1 (2015-01-22)


#### Bug Fixes

* **ApplicationRepresentation:** change icon to iconId ([951e03ab](https://github.com/ozone-development/ozp-rest/commit/951e03ab02d77e3c2cf4468320c72baf1a82f024))


## 0.5.0 (2015-01-22)


#### Bug Fixes

* **Exceptions:** OZF-827 Re-add exception messages that were accidentially suppressed ([4c5bd41b](https://github.com/ozone-development/ozp-rest/commit/4c5bd41b2fcefe8fc4cc0ac3bc435fc9e58f418b))
* **HTTP Caching:**
  * Missing config to enable caching annotation ([dd294288](https://github.com/ozone-development/ozp-rest/commit/dd294288d92719c2c15291bda29ca1887f3503de))
  * Adding no-cache headers to most responses ([1bfb9c33](https://github.com/ozone-development/ozp-rest/commit/1bfb9c33f63ecfa639a36acf5f7ad33e268d7d46))
* **Library:** OZF-732 Fix app lib PUT ([aed8ca09](https://github.com/ozone-development/ozp-rest/commit/aed8ca091e80674861f8e6011c6b05a23dea727f))
* **URIs:** OZF-821 Explicit configuration for system public URI ([2a09b296](https://github.com/ozone-development/ozp-rest/commit/2a09b29638a3b509c435bf2e48a7ca3cd43c40e5))


#### Features

* **Image Server:**
  * OZF-446 Store icons and images locally ([a08222ca](https://github.com/ozone-development/ozp-rest/commit/a08222cabe6ab941c6dbea32cd391d488dde82be))
  * Another attempt to fix globs on Windows ([6853c1c3](https://github.com/ozone-development/ozp-rest/commit/6853c1c3e87454b3dd6d9f19fd5c6f4592ed6e9a))
  * Possible fix for Windows image path matching issue ([5d324b8e](https://github.com/ozone-development/ozp-rest/commit/5d324b8efe6f289626c5b11cd4c6f3b5a9da8f19))
  * Fix NullPointerException in marketplace.converter.UUIDConverter ([b3c1836e](https://github.com/ozone-development/ozp-rest/commit/b3c1836eca063e131672a62c10e56ead50deafd3))
  * Refine error responses ([9e30e31a](https://github.com/ozone-development/ozp-rest/commit/9e30e31ade44d89906ba1bfef8a9843d95e71025))
  * Tweaking Content-Types for IE workaround ([d22551a2](https://github.com/ozone-development/ozp-rest/commit/d22551a23dd4aeb8a591137672cd353a13c60c4a))
  * Allowing ThrowableWriter to respond to any Accept header ([e02704d8](https://github.com/ozone-development/ozp-rest/commit/e02704d817988c83be471b5d4826f46e750f952d))
  * fix build config for creating wars ([9c246a52](https://github.com/ozone-development/ozp-rest/commit/9c246a52f359fc8a5d49a59dfe6f3af43524c858))
  * getImageReference handle missing directory ([3c2ecedd](https://github.com/ozone-development/ozp-rest/commit/3c2ecedd70495be3dbc430923cf1b944a14b2309))
  * OZF-740 Correct HTTP response for invalid image id on listing ([f143c5cf](https://github.com/ozone-development/ozp-rest/commit/f143c5cf8fc4dd4620cc670be029397a0c7bdb02))
  * OZF-739 Checking intent for invalid iconId ([fadd1044](https://github.com/ozone-development/ozp-rest/commit/fadd1044af2ca95823bd531be0bbd14c83f20ea2))
  * OZF-739 Use Image Server for intents icons ([ebe85b15](https://github.com/ozone-development/ozp-rest/commit/ebe85b15cdda8b565a3169e0a24b4c1be0324185))
  * OZF-733 Fix null image id handling ([eb706330](https://github.com/ozone-development/ozp-rest/commit/eb706330f2ab66aeb8bf6074b8108aeaf9ff0b9c))
  * OZF-738 Fix listing null image issue ([f8008ef4](https://github.com/ozone-development/ozp-rest/commit/f8008ef442a378782fbf33401fe3e0c79cc3bf66))
  * OZF-737 Optimize delete logging and hopefully avoid bug ([d7842a14](https://github.com/ozone-development/ozp-rest/commit/d7842a1446ad234d3d3ddb37abd1e0a8b458754d))
  * Add image id validation for listing update ([410be694](https://github.com/ozone-development/ozp-rest/commit/410be69430a78fe397cec0dd372d97c623c973a5))
  * Image CEF logging ([74709662](https://github.com/ozone-development/ozp-rest/commit/7470966212cffa3b56fb115a905859ed98ca5335))
  * Update security plugin with new ehcache settings ([f393c063](https://github.com/ozone-development/ozp-rest/commit/f393c063faf187cad7b6764f0e9fecd1c66648c0))
  * Disallowing empty images ([89276dfb](https://github.com/ozone-development/ozp-rest/commit/89276dfb3a3bb3262f4d1d9dd9b3993321a7747f))
  * Fix refactor of ApplicationLibrary reps ([db115fed](https://github.com/ozone-development/ozp-rest/commit/db115feda4f1bf9a72f14fea618e4a0ec4c5af19))
  * Fix additional representation image links ([7fd64279](https://github.com/ozone-development/ozp-rest/commit/7fd64279e4a30c306442db0ae1eaefc67ae2ffb4))
  * ImageRestService fixes ([f85891b7](https://github.com/ozone-development/ozp-rest/commit/f85891b77dad72b93b92549d69b5a5b3eb29f785))
  * Aggressive HTTP caching ([783d0d03](https://github.com/ozone-development/ozp-rest/commit/783d0d0355d32d16fc8cef047690c580aaf0c3ef))
  * Improve login performance ([4759f659](https://github.com/ozone-development/ozp-rest/commit/4759f659d9473af2a83d1dc8131e7adf8a2dd03a))
  * Fix elasticsearch indexing ([8c0507d7](https://github.com/ozone-development/ozp-rest/commit/8c0507d7f5cd4a7efa49294add6e9082c1b84bc8))
  * Fix image REST issues ([f348a074](https://github.com/ozone-development/ozp-rest/commit/f348a074952907850275fee360c7a75c0e390d88))
  * Switch to purely filesystem-based image storage ([3e541ec8](https://github.com/ozone-development/ozp-rest/commit/3e541ec894db071e20911e8a3f768953786e04db))
  * Optimized cleanup query ([5a065f15](https://github.com/ozone-development/ozp-rest/commit/5a065f15b7731af5e20e2c59f9da7cab5d397e59))
  * Validation for max size and allowed content type ([968ff5c6](https://github.com/ozone-development/ozp-rest/commit/968ff5c659bb291c37838b54391f33dc8e1996a2))
  * Optimize n+1 SQL query in image cleanup job ([eef800b2](https://github.com/ozone-development/ozp-rest/commit/eef800b2ff87ba627664ddb2a5e668afbac67a1b))
  * Image cleanup job ([45309061](https://github.com/ozone-development/ozp-rest/commit/45309061432359805a81e5ddca2b360f32c4730c))
  * Fix Agency searchable mapping ([288015f8](https://github.com/ozone-development/ozp-rest/commit/288015f87aa6b84107f162f7038551454a8fe6d0))
  * Update Agency ([5218a971](https://github.com/ozone-development/ozp-rest/commit/5218a971f687561a283b4101487fe5c464899098))
  * add image references to reps and search index ([70c3e19d](https://github.com/ozone-development/ozp-rest/commit/70c3e19d85cb318f9c0eff8a519012e7b00028ab))
  * form-data endpoint for compatibility ([95dc5543](https://github.com/ozone-development/ozp-rest/commit/95dc5543974a64e5e6f591a2235be4b722f4e484))
  * Switch to file-backed image server and individual uploads ([cc6e1a42](https://github.com/ozone-development/ozp-rest/commit/cc6e1a42aec96f42edbb998e23f4caeeb12f7eea))
  * Use Image class for listings ([18f54eb9](https://github.com/ozone-development/ozp-rest/commit/18f54eb9b0ca8955c41d437d5a41868fdd787b3a))
  * Initial services and resources ([467c2473](https://github.com/ozone-development/ozp-rest/commit/467c2473f80e3a9c4dd881255fcdb8299c810dc7))
  * Initial Image domain class ([a9dcdf0f](https://github.com/ozone-development/ozp-rest/commit/a9dcdf0f921a8cf2ea56484d62ba7b69856a275a))
* **Review Tab:** OZF-483: getting appropriate information in the JSON response ([cdcead7d](https://github.com/ozone-development/ozp-rest/commit/cdcead7dc8e3733e53b99b70cb2e25e831c848ea))
* **Reviews Tab:**
  * OZF-483 fix typo ([82139643](https://github.com/ozone-development/ozp-rest/commit/821396437ebe91f489a8c1d47008da5998ac654d))
  * OZF-483 bug fix updatedDate should be editedDate ([fd16b4c4](https://github.com/ozone-development/ozp-rest/commit/fd16b4c4d4874c6162c184c6236a765557f3b90f))
  * OZF-483: include profile id, createdDate, updatedDate with review ([43289791](https://github.com/ozone-development/ozp-rest/commit/43289791259bb53a0ee48fe3ec90d609164ead70))
  * OZF-483: include profile with review ([5c49ecb9](https://github.com/ozone-development/ozp-rest/commit/5c49ecb96e4f5c0eddffafaae52011ca2aaf5ad2))


### 0.4.1 (2015-01-07)


#### Bug Fixes

* **Application Library:** Removing broken support for application/hal+json ([746766f9](https://github.com/ozone-development/ozp-rest/commit/746766f9441c1948e29c65bc32ce6eee330ce05e))
* **Listing:** OZF-567: fixed broken self links for required and requiring listings ([79e4e60a](https://github.com/ozone-development/ozp-rest/commit/79e4e60a15b74d931a0b0a6e179cb40804cfd483))


### 0.4.1 (2015-01-07)


#### Bug Fixes

* **Application Library:** Removing broken support for application/hal+json ([746766f9](https://github.com/ozone-development/ozp-rest/commit/746766f9441c1948e29c65bc32ce6eee330ce05e))
* **Listing:** OZF-567: fixed broken self links for required and requiring listings ([79e4e60a](https://github.com/ozone-development/ozp-rest/commit/79e4e60a15b74d931a0b0a6e179cb40804cfd483))


### 0.4.1 (2014-12-11)


#### Bug Fixes

* **Listing:**
  * Fix to Listing REST call ([adad87ac](https://github.com/ozone-development/ozp-rest/commit/adad87acee1910b97329edbd07447396f7c5a864))
  * Listing REST call not working for MySQL ([9629da2a](https://github.com/ozone-development/ozp-rest/commit/9629da2ae245e4c7eb70cc0fdb945d97d3a3db59))


## 0.4.0 (2014-12-09)


#### Bug Fixes

* **Agency:**
  * OZF-373 Fixing access control for agency DELETE ([6a5ae1be](https://github.com/ozone-development/ozp-rest/commit/6a5ae1be7562b335d32b48161ebfb28f8ed92aea))
  * OZF-313 Proper error when agency deletion fails due to existing listings ([c7bf0b2b](https://github.com/ozone-development/ozp-rest/commit/c7bf0b2b5deaa086f8431088fc10cb5dd82943d1))
  * Fixing null icon ([f4384d88](https://github.com/ozone-development/ozp-rest/commit/f4384d880b217b0280275bbae1ceb0eca75e9be1))
  * Adding missing unique constraint ([80a2afa1](https://github.com/ozone-development/ozp-rest/commit/80a2afa15920622336da00da9a7c7241142633d8))
* **Agency and Intents:** Agency and Intents are not throwing validation errors with regards to uniqueness ([ccac9ea4](https://github.com/ozone-development/ozp-rest/commit/ccac9ea4927ceffe245a15282d2fe47fa9cd1d38))
* **Application Library:** fixing Application Library HAL representation ([90ddfc31](https://github.com/ozone-development/ozp-rest/commit/90ddfc312fb882b49c66867fff786e8bb79d52ac))
* **Auditing:** Corrected the way the audit trail looks up the current user ([e739be75](https://github.com/ozone-development/ozp-rest/commit/e739be7518d242c1d6dbeb0e1dcc2a6de396bbad))
* **Category:**
  * text for category delete fail was ambigious. ([17710cbc](https://github.com/ozone-development/ozp-rest/commit/17710cbc093bf539804c5cf9c4d5ba21224fb2e6))
  * Fixing CategoryResource constructor ([289840e4](https://github.com/ozone-development/ozp-rest/commit/289840e441e304830b2f696dcdab6bd85d0ae0c7))
* **Category API:** categories are not deleted if any listings are present in the system ([9d2036b3](https://github.com/ozone-development/ozp-rest/commit/9d2036b3a708a1a03309ac745213dade28dbf24e))
* **Contacts:** OZF-492 Loosening rules for required Contact Types ([73339775](https://github.com/ozone-development/ozp-rest/commit/733397755de6f742d63427dcbc41ebeff72cf625))
* **Draft Listing:** Refining non-draft requirements ([bb7156af](https://github.com/ozone-development/ozp-rest/commit/bb7156af89ec4957b4e46f7a251c11115730deae))
* **Draft Listings:**
  * OZF-436 Correcting non-draft required fields ([fbc42227](https://github.com/ozone-development/ozp-rest/commit/fbc42227887163d60e623cb451869c0edb7504ba))
  * Making width and height nullable ([74a01b18](https://github.com/ozone-development/ozp-rest/commit/74a01b18a1b29ba5f1972219b6bfcea36a6e1fac))
  * Removing docUrls from non-draft required fields ([f0a06df4](https://github.com/ozone-development/ozp-rest/commit/f0a06df4516754e86c3d4c21eb2f8f1f939322a1))
* **Error Status Codes:** certain types are returning a 500 status code when a unique constraint is violat ([08480b84](https://github.com/ozone-development/ozp-rest/commit/08480b84ee5dc64a851bdcb6fb753e5f97f41b08))
* **IWC Application API:** Adding icons to the application representation ([fb6cb246](https://github.com/ozone-development/ozp-rest/commit/fb6cb2466c823fb334558eb740bb20b41b1f7480))
* **IWC Data API:** fixing entity size and resource status codes ([b240be02](https://github.com/ozone-development/ozp-rest/commit/b240be027d827368bd95874c2c1db7ca0e02ab71))
* **Intents:** Trying to modify immutable properties now returns the proper status code and a m ([8427cc8f](https://github.com/ozone-development/ozp-rest/commit/8427cc8f392a74461484ef347ee67fc0f23dd4eb))
* **ItemComment:**
  * OZF-375 Adding missing ItemComment ListingActivity logic ([3393f1f7](https://github.com/ozone-development/ozp-rest/commit/3393f1f75e36897dc287bd6f5015a7e96e120bf8))
  * OZF-283 optional comment ratings ([2e8134df](https://github.com/ozone-development/ozp-rest/commit/2e8134df6b89af5385dd12b1eec3c52b58778fcd))
  * Fixing rate parse error handling ([18bbf59d](https://github.com/ozone-development/ozp-rest/commit/18bbf59d7013c601540d9d2783e7ca62578a8628))
* **IwcApiRepresentation:** Fixing MissingMethodExceptions ([459052ca](https://github.com/ozone-development/ozp-rest/commit/459052ca64977bca5cf37e427b8a9d9d35de76a9))
* **IwcUserApplicationsRepresentation:** Fixing spelling ([2cd23d17](https://github.com/ozone-development/ozp-rest/commit/2cd23d1751aa0fcff16bb4ec68a4ccedc996ab24))
* **Listing:**
  * singleton is missing from the Listing Representation ([b5149b2e](https://github.com/ozone-development/ozp-rest/commit/b5149b2e218cc3d2f6f879ee94120008beca40fd))
  * changing approvalStatus representation ([79003902](https://github.com/ozone-development/ozp-rest/commit/7900390259de3d2fa868b14d08a40d76f27e0f70))
* **Listing API:**
  * OZF-534. Saving a listing fails when agency is null ([4f0d9e86](https://github.com/ozone-development/ozp-rest/commit/4f0d9e868a4afef8395e2664477765f60512722a))
  * listing collections are returning 404 rather than a default representation when  ([db725dcc](https://github.com/ozone-development/ozp-rest/commit/db725dcca0babf08e3f6d82633e68a7329506b5b))
  * editedData is missing from the listing representation ([234ab6ff](https://github.com/ozone-development/ozp-rest/commit/234ab6ff112dae8b71ea8786565728ae48394e55))
  * fixing a compilation error in the Listing RepresentationWriter ([1a00c208](https://github.com/ozone-development/ozp-rest/commit/1a00c2081d32e600d4f7ba532ab9c12371e7cbbf))
  * when a child item in a collection is not valid, a 500 error is returned rather t ([c1a3db30](https://github.com/ozone-development/ozp-rest/commit/c1a3db30b46de31f23fe65fdd56aac9cb6858228))
  * required contact type constraint is not being enforced ([ced80992](https://github.com/ozone-development/ozp-rest/commit/ced809924c5b55e33ea97598fd84f5a160786c6f))
* **Listing Activity:**
  * adding author as a property to Listing Activity Representation ([78f1effc](https://github.com/ozone-development/ozp-rest/commit/78f1effcd47ead8a73f1faf5b662a6f3f5b4802d))
  * OZF-379 Fixing Profile Listing Activity representations ([7b655b88](https://github.com/ozone-development/ozp-rest/commit/7b655b88c1683b7d91b487e5ea32830332726a9a))
  * OZF-374 Adding needed fields for Rejection Activities ([a85051d3](https://github.com/ozone-development/ozp-rest/commit/a85051d3100e89f4fa6d328c20bd6c311b469c2a))
* **Listing Activity Representation:** Adding missing HAL classes for ListingActivity ([678a2ed3](https://github.com/ozone-development/ozp-rest/commit/678a2ed336d0f5f18da5dc357b0f92a74602b3a7))
* **Profile:**
  * the user's highest role should be part of the Profile Representation ([2794b916](https://github.com/ozone-development/ozp-rest/commit/2794b9162a1e58d4e1cc1d0d2f1ef4ca0cf7a21f))
  * OZF-376 Adding id to ProfileRepresentation ([3cd9763b](https://github.com/ozone-development/ozp-rest/commit/3cd9763b707d8e0348d47b989c604b19f02c5a1b))
* **Profile Representation:** OZF-354 allowing ProfileRepresentation for application/json ([00395ac6](https://github.com/ozone-development/ozp-rest/commit/00395ac661ba0b6ae6854343ede0d5f46456016b))
* **REST:**
  * add uuid to /profile/self/library ([59dcc700](https://github.com/ozone-development/ozp-rest/commit/59dcc700d3d66ac5bb057ed7f90beb9353c2f54b))
  * fix runtime error in /profile/self/listing ([ca4acb01](https://github.com/ozone-development/ozp-rest/commit/ca4acb0133fd2a5c5ab83faec9262ed82b6b2951))
  * OZF-377 Removing Content-Type restrictions from DELETE methods ([0b34fddf](https://github.com/ozone-development/ozp-rest/commit/0b34fddf2b195d9978f1495e4133ca3aeffbd8ee))
* **Rejection:** OZF-300 Correcting auth error ([84e70c62](https://github.com/ozone-development/ozp-rest/commit/84e70c62b55ba6f9a30ec81fed772eeecedcbca5))
* **Required Listings:**
  * OZF-563 Removing self from requiringListings response ([7f699230](https://github.com/ozone-development/ozp-rest/commit/7f699230fa7d1e69773fe18d0727d1b37c5eea36))
  * OZF-562 Fix delete of listing with required listings ([56fb89ea](https://github.com/ozone-development/ozp-rest/commit/56fb89eab176068bc563b83ac6d95652fd0902a4))
* **RestService:** Properties of domain classes that are collections are not updating properly ([7d925596](https://github.com/ozone-development/ozp-rest/commit/7d92559607bd6454d16fb4129e669536aa151704))
* **Reviews:** OZF-661 Delete comment throwing an error ([81fe8766](https://github.com/ozone-development/ozp-rest/commit/81fe8766a34337d3a62f71845dc83d92b240205e))
* **Sample data:** adding the data directory ([7d51378e](https://github.com/ozone-development/ozp-rest/commit/7d51378e10d0ef25b7aeaeb4138cb54b62d3449a))
* **Search:**
  * Adding secondary and tertiary sort order ([08936eed](https://github.com/ozone-development/ozp-rest/commit/08936eeda054451ca808a0d2b889c76be950708a))
  * Approval status of search results is always being reported as IN_PROGRESS ([4c97a0de](https://github.com/ozone-development/ozp-rest/commit/4c97a0deab06e55df6155b099bdc126e765e1ad3))
  * Fixing search in reference to UriBuilder refactor ([d63f1f9e](https://github.com/ozone-development/ozp-rest/commit/d63f1f9ebf684d8a333e6607e7bf3a27d5429e4a))
  * the queryString param is not appearing in the self link for a search result ([389c4923](https://github.com/ozone-development/ozp-rest/commit/389c4923a22d2f22e1b05663bc2a107a1a2c03bf))
* **Search API:** filtering by category uses the wrong query param ([14466cc9](https://github.com/ozone-development/ozp-rest/commit/14466cc9fb0b521dcf9a83be23d5ab5ebbdf1063))
* **Search Ids:** OZF-551 Upgrading elasticsearch plugin ([0bc76175](https://github.com/ozone-development/ozp-rest/commit/0bc7617519d4dede4812a2e935db3b45ae9b7563))
* **Tags:** Fixing Tag length validation ([c0c6b095](https://github.com/ozone-development/ozp-rest/commit/c0c6b095d57a4a11c78e143d26009da781d44856))
* **sample data:** add loadSampleData script for convenience ([8ca4b879](https://github.com/ozone-development/ozp-rest/commit/8ca4b87949e8439ead4aa113730caaf8e7d718de))


#### Features

* **Agency API:** Adding shortname to agency ([5e60ab72](https://github.com/ozone-development/ozp-rest/commit/5e60ab72d5acfa290dc76c60c03f481390a52540))
* **ChildObjectCollection:** Removing now-redundant classes StewardedOrganizations and ApplicationLibrary ([267d4be7](https://github.com/ozone-development/ozp-rest/commit/267d4be7a94d0a50081c58038a80890c7f58ec86))
* **Draft Listing:** OZF-416 OZF-417 Validation of non-draft-required fields ([e9d21030](https://github.com/ozone-development/ozp-rest/commit/e9d210306860ab4e19a7f7caba954d6a71145c94))
* **Draft Listings:** OZF-170 ([ba735749](https://github.com/ozone-development/ozp-rest/commit/ba735749aaebaff8496d22dfa9311d680435830e))
* **Error messages:** Improve Error messages being returned to client from Rest services ([7b9b1c34](https://github.com/ozone-development/ozp-rest/commit/7b9b1c34db3d290d0ffea31e2b4c7ffd77898c02))
* **Featured:**
  * OZF-631 Add access controls for featured flag ([9d9d7765](https://github.com/ozone-development/ozp-rest/commit/9d9d77655ecef775ede2ce37a99b396215a8e567))
  * Add featured-flag changes the Modified changelog entries ([28268c20](https://github.com/ozone-development/ozp-rest/commit/28268c202dcb2181d371eadf06b159bfc009e1af))
  * OZF-631 OZF-628 isFeatured backend access controls ([4bfb75ba](https://github.com/ozone-development/ozp-rest/commit/4bfb75ba2f6e5fb847dfdedcd5138f9b8e788dab))
* **HAL:**
  * Listing representations from search queries should be in HAL format ([f1b6e8db](https://github.com/ozone-development/ozp-rest/commit/f1b6e8dbac5872f9ef827014c49ae95bab4753dd))
  * Contact Type HAL ([58b318f3](https://github.com/ozone-development/ozp-rest/commit/58b318f3d32a70672c8d58cce7b7fb83e70be073))
  * Agency HAL reps ([a8854293](https://github.com/ozone-development/ozp-rest/commit/a88542936719eceeb2f3cdef7c78fdbb109c90d4))
  * Intents Collection HAL ([8d5f9720](https://github.com/ozone-development/ozp-rest/commit/8d5f9720edcf5524ed758fccf600094e997cc095))
  * Converting Type to HAL ([16fcc1d1](https://github.com/ozone-development/ozp-rest/commit/16fcc1d10ddb61287d3b867a4dbfe31898db793b))
  * fixing Category content-type ([eaf0eed4](https://github.com/ozone-development/ozp-rest/commit/eaf0eed4307be12816369306c52c4805ba3f1254))
  * Completing Category HAL transition ([38cdf7c9](https://github.com/ozone-development/ozp-rest/commit/38cdf7c9690eb61c3c727b579e852426ac0b03c2))
  * Category reps for HAL ([3960376d](https://github.com/ozone-development/ozp-rest/commit/3960376d58ef84ba4359eb1ecdcb5e28aa36649e))
  * RejectionListing HAL ([9c23f37e](https://github.com/ozone-development/ozp-rest/commit/9c23f37ef197e6e2370c2c161eeed0e63d8b6362))
* **Listing:** Adding singleton, height && width properties to Listing ([6cfb32f6](https://github.com/ozone-development/ozp-rest/commit/6cfb32f666c538bd730b71f11515be59566d6601))
* **Listing Activity:** Paging support for listing activity representation ([09e0649d](https://github.com/ozone-development/ozp-rest/commit/09e0649d1ae0be5004ed86e805b283f1692232df))
* **Listing Admin:**
  * OZF-464 Parameterized Listing Admin REST call ([99fd3cab](https://github.com/ozone-development/ozp-rest/commit/99fd3cab8858aa4c1e6ad6d3e779b726045d1cb9))
  * Adding zero entries for empty agency counts ([47d3b45a](https://github.com/ozone-development/ozp-rest/commit/47d3b45a7eb6c7196aa5604b3b71918804a085c5))
  * Fixing counts for org stewards ([67a52e04](https://github.com/ozone-development/ozp-rest/commit/67a52e04db9dcf739828e461f21a843f36ce2e6f))
  * Fixing org steward detection ([7e8a0751](https://github.com/ozone-development/ozp-rest/commit/7e8a075129265d6336c12e3f3ce3d679ba498145))
  * Org Steward listing admin REST call ([73c5c8e6](https://github.com/ozone-development/ozp-rest/commit/73c5c8e679c54ca2059b09a2fc4efc13adcb7d79))
  * Fix count when using approvalStatus parameter ([d895ac2e](https://github.com/ozone-development/ozp-rest/commit/d895ac2e3fea28701522c02e6d6cae96d8cd7734))
  * Adding query params to HAL URIs ([e660af83](https://github.com/ozone-development/ozp-rest/commit/e660af83201fe263fc4878e6cd6ffd49b62aa340))
  * Listing counts ([c4b01d78](https://github.com/ozone-development/ozp-rest/commit/c4b01d7885331796b4d9efa66e572a928c9cc6cc))
  * Add basic REST call to get all by parameters ([b24561a1](https://github.com/ozone-development/ozp-rest/commit/b24561a1c7bb918a472ca7921bfa05b90400f8f5))
* **OZF-268 Approval:** Removing old RejectionJustification class ([ce8fce0b](https://github.com/ozone-development/ozp-rest/commit/ce8fce0b4807266c3e714a2abf00bb0d51ec084c))
* **Profile API:** sort by display name ([88e1b7a4](https://github.com/ozone-development/ozp-rest/commit/88e1b7a4b13df37308365632d53d9575614745dd))
* **Required Listings:**
  * OZF-564 Add required listing info to changelog ([486e8866](https://github.com/ozone-development/ozp-rest/commit/486e8866d8d957c7efd2d887196270490eca0484))
  * OZF-308 Add required listings to ListingRepresentation ([d9a628f7](https://github.com/ozone-development/ozp-rest/commit/d9a628f742650e70d8b4fa39614c5c57b5d96afd))
  * Simplifying and restoring required listings functionality ([c3ce99e0](https://github.com/ozone-development/ozp-rest/commit/c3ce99e09db626b50043dd2f9efa742c37990c82))
* **Steward Approval:**
  * Correct permissions and flow for rejection ([e8b1711a](https://github.com/ozone-development/ozp-rest/commit/e8b1711a22517eb4004b8637747a3c40ed241c81))
  * Add steward users in dev mode ([eff436d0](https://github.com/ozone-development/ozp-rest/commit/eff436d02795ea7b831030782c9d3a013f0d79f0))
  * Add APPROVED_ORG ApprovalStatus and Action ([bb164e3c](https://github.com/ozone-development/ozp-rest/commit/bb164e3cf7b9dd06d2f0eafe18d24be224ee9762))


#### Breaking Changes

* error response structure is changed

To migrate the code follow the example below:

Before:

{
    error: true,
    message: ‘’,
    resolvedMessages: []
}

After:

{
    message: ‘’,
    errors: {
        ‘fieldName’: ‘error message’,
        ‘fieldName2’: [‘error message’, ‘error message 2 ’] // if
errors are more than 1
    }
}

 ([7b9b1c34](https://github.com/ozone-development/ozp-rest/commit/7b9b1c34db3d290d0ffea31e2b4c7ffd77898c02))
* The following fields must have non-empty values for
    listings which are not IN_PROGRESS.  400 errors will otherwise
    result.

    width
    height
    whatIsNew
    descriptionShort
    isFeatured
    description
    versionName
    requirements
    agency
    launchUrl
    categories
    imageSmallUrl
    imageMediumUrl
    imageLargeUrl
    imageXlargeUrl
    contacts
    screenshots

    This is is addition to fields required for all Listings
    (title and type)

 ([ba735749](https://github.com/ozone-development/ozp-rest/commit/ba735749aaebaff8496d22dfa9311d680435830e))


## 0.3.0 (2014-11-26)


#### Bug Fixes

* **Contacts:** OZF-492 Loosening rules for required Contact Types ([73339775](https://github.com/ozone-development/ozp-rest/commit/733397755de6f742d63427dcbc41ebeff72cf625))
* **Listing API:** OZF-534. Saving a listing fails when agency is null ([4f0d9e86](https://github.com/ozone-development/ozp-rest/commit/4f0d9e868a4afef8395e2664477765f60512722a))
* **Required Listings:**
  * OZF-563 Removing self from requiringListings response ([7f699230](https://github.com/ozone-development/ozp-rest/commit/7f699230fa7d1e69773fe18d0727d1b37c5eea36))
  * OZF-562 Fix delete of listing with required listings ([56fb89ea](https://github.com/ozone-development/ozp-rest/commit/56fb89eab176068bc563b83ac6d95652fd0902a4))


#### Features

* **Required Listings:**
  * OZF-564 Add required listing info to changelog ([486e8866](https://github.com/ozone-development/ozp-rest/commit/486e8866d8d957c7efd2d887196270490eca0484))
  * OZF-308 Add required listings to ListingRepresentation ([d9a628f7](https://github.com/ozone-development/ozp-rest/commit/d9a628f742650e70d8b4fa39614c5c57b5d96afd))
  * Simplifying and restoring required listings functionality ([c3ce99e0](https://github.com/ozone-development/ozp-rest/commit/c3ce99e09db626b50043dd2f9efa742c37990c82))


## 0.2.0 (2014-11-12)


#### Bug Fixes

* **Agency:**
  * OZF-373 Fixing access control for agency DELETE ([6a5ae1be](https://github.com/ozone-development/ozp-rest/commit/6a5ae1be7562b335d32b48161ebfb28f8ed92aea))
  * OZF-313 Proper error when agency deletion fails due to existing listings ([c7bf0b2b](https://github.com/ozone-development/ozp-rest/commit/c7bf0b2b5deaa086f8431088fc10cb5dd82943d1))
  * Fixing null icon ([f4384d88](https://github.com/ozone-development/ozp-rest/commit/f4384d880b217b0280275bbae1ceb0eca75e9be1))
  * Adding missing unique constraint ([80a2afa1](https://github.com/ozone-development/ozp-rest/commit/80a2afa15920622336da00da9a7c7241142633d8))
* **Agency and Intents:** Agency and Intents are not throwing validation errors with regards to uniqueness ([ccac9ea4](https://github.com/ozone-development/ozp-rest/commit/ccac9ea4927ceffe245a15282d2fe47fa9cd1d38))
* **Application Library:** fixing Application Library HAL representation ([90ddfc31](https://github.com/ozone-development/ozp-rest/commit/90ddfc312fb882b49c66867fff786e8bb79d52ac))
* **Auditing:** Corrected the way the audit trail looks up the current user ([e739be75](https://github.com/ozone-development/ozp-rest/commit/e739be7518d242c1d6dbeb0e1dcc2a6de396bbad))
* **Category:**
  * text for category delete fail was ambigious. ([17710cbc](https://github.com/ozone-development/ozp-rest/commit/17710cbc093bf539804c5cf9c4d5ba21224fb2e6))
  * Fixing CategoryResource constructor ([289840e4](https://github.com/ozone-development/ozp-rest/commit/289840e441e304830b2f696dcdab6bd85d0ae0c7))
* **Category API:** categories are not deleted if any listings are present in the system ([9d2036b3](https://github.com/ozone-development/ozp-rest/commit/9d2036b3a708a1a03309ac745213dade28dbf24e))
* **Draft Listings:**
  * Refining non-draft requirements ([bb7156af](https://github.com/ozone-development/ozp-rest/commit/bb7156af89ec4957b4e46f7a251c11115730deae))
  * OZF-436 Correcting non-draft required fields ([fbc42227](https://github.com/ozone-development/ozp-rest/commit/fbc42227887163d60e623cb451869c0edb7504ba))
  * Making width and height nullable ([74a01b18](https://github.com/ozone-development/ozp-rest/commit/74a01b18a1b29ba5f1972219b6bfcea36a6e1fac))
  * Removing docUrls from non-draft required fields ([f0a06df4](https://github.com/ozone-development/ozp-rest/commit/f0a06df4516754e86c3d4c21eb2f8f1f939322a1))
* **Error Status Codes:** certain types are returning a 500 status code when a unique constraint is violat ([08480b84](https://github.com/ozone-development/ozp-rest/commit/08480b84ee5dc64a851bdcb6fb753e5f97f41b08))
* **IWC Application API:** Adding icons to the application representation ([fb6cb246](https://github.com/ozone-development/ozp-rest/commit/fb6cb2466c823fb334558eb740bb20b41b1f7480))
* **IWC Data API:** fixing entity size and resource status codes ([b240be02](https://github.com/ozone-development/ozp-rest/commit/b240be027d827368bd95874c2c1db7ca0e02ab71))
* **Intents:** Trying to modify immutable properties now returns the proper status code and a m ([8427cc8f](https://github.com/ozone-development/ozp-rest/commit/8427cc8f392a74461484ef347ee67fc0f23dd4eb))
* **ItemComment:**
  * OZF-375 Adding missing ItemComment ListingActivity logic ([3393f1f7](https://github.com/ozone-development/ozp-rest/commit/3393f1f75e36897dc287bd6f5015a7e96e120bf8))
  * OZF-283 optional comment ratings ([2e8134df](https://github.com/ozone-development/ozp-rest/commit/2e8134df6b89af5385dd12b1eec3c52b58778fcd))
  * Fixing rate parse error handling ([18bbf59d](https://github.com/ozone-development/ozp-rest/commit/18bbf59d7013c601540d9d2783e7ca62578a8628))
* **IwcApiRepresentation:** Fixing MissingMethodExceptions ([459052ca](https://github.com/ozone-development/ozp-rest/commit/459052ca64977bca5cf37e427b8a9d9d35de76a9))
* **IwcUserApplicationsRepresentation:** Fixing spelling ([2cd23d17](https://github.com/ozone-development/ozp-rest/commit/2cd23d1751aa0fcff16bb4ec68a4ccedc996ab24))
* **Listing:**
  * singleton is missing from the Listing Representation ([b5149b2e](https://github.com/ozone-development/ozp-rest/commit/b5149b2e218cc3d2f6f879ee94120008beca40fd))
  * changing approvalStatus representation ([79003902](https://github.com/ozone-development/ozp-rest/commit/7900390259de3d2fa868b14d08a40d76f27e0f70))
* **Listing API:**
  * listing collections are returning 404 rather than a default representation when  ([db725dcc](https://github.com/ozone-development/ozp-rest/commit/db725dcca0babf08e3f6d82633e68a7329506b5b))
  * editedData is missing from the listing representation ([234ab6ff](https://github.com/ozone-development/ozp-rest/commit/234ab6ff112dae8b71ea8786565728ae48394e55))
  * fixing a compilation error in the Listing RepresentationWriter ([1a00c208](https://github.com/ozone-development/ozp-rest/commit/1a00c2081d32e600d4f7ba532ab9c12371e7cbbf))
  * when a child item in a collection is not valid, a 500 error is returned rather t ([c1a3db30](https://github.com/ozone-development/ozp-rest/commit/c1a3db30b46de31f23fe65fdd56aac9cb6858228))
  * required contact type constraint is not being enforced ([ced80992](https://github.com/ozone-development/ozp-rest/commit/ced809924c5b55e33ea97598fd84f5a160786c6f))
* **Listing Activity:**
  * adding author as a property to Listing Activity Representation ([78f1effc](https://github.com/ozone-development/ozp-rest/commit/78f1effcd47ead8a73f1faf5b662a6f3f5b4802d))
  * OZF-379 Fixing Profile Listing Activity representations ([7b655b88](https://github.com/ozone-development/ozp-rest/commit/7b655b88c1683b7d91b487e5ea32830332726a9a))
  * OZF-374 Adding needed fields for Rejection Activities ([a85051d3](https://github.com/ozone-development/ozp-rest/commit/a85051d3100e89f4fa6d328c20bd6c311b469c2a))
* **Listing Activity Representation:** Adding missing HAL classes for ListingActivity ([678a2ed3](https://github.com/ozone-development/ozp-rest/commit/678a2ed336d0f5f18da5dc357b0f92a74602b3a7))
* **Profile:**
  * the user's highest role should be part of the Profile Representation ([2794b916](https://github.com/ozone-development/ozp-rest/commit/2794b9162a1e58d4e1cc1d0d2f1ef4ca0cf7a21f))
  * OZF-376 Adding id to ProfileRepresentation ([3cd9763b](https://github.com/ozone-development/ozp-rest/commit/3cd9763b707d8e0348d47b989c604b19f02c5a1b))
* **Profile Representation:** OZF-354 allowing ProfileRepresentation for application/json ([00395ac6](https://github.com/ozone-development/ozp-rest/commit/00395ac661ba0b6ae6854343ede0d5f46456016b))
* **REST:**
  * add uuid to /profile/self/library ([59dcc700](https://github.com/ozone-development/ozp-rest/commit/59dcc700d3d66ac5bb057ed7f90beb9353c2f54b))
  * fix runtime error in /profile/self/listing ([ca4acb01](https://github.com/ozone-development/ozp-rest/commit/ca4acb0133fd2a5c5ab83faec9262ed82b6b2951))
  * OZF-377 Removing Content-Type restrictions from DELETE methods ([0b34fddf](https://github.com/ozone-development/ozp-rest/commit/0b34fddf2b195d9978f1495e4133ca3aeffbd8ee))
* **Rejection:** OZF-300 Correcting auth error ([84e70c62](https://github.com/ozone-development/ozp-rest/commit/84e70c62b55ba6f9a30ec81fed772eeecedcbca5))
* **RestService:** Properties of domain classes that are collections are not updating properly ([7d925596](https://github.com/ozone-development/ozp-rest/commit/7d92559607bd6454d16fb4129e669536aa151704))
* **Sample data:** adding the data directory ([7d51378e](https://github.com/ozone-development/ozp-rest/commit/7d51378e10d0ef25b7aeaeb4138cb54b62d3449a))
* **Search:**
  * Adding secondary and tertiary sort order ([08936eed](https://github.com/ozone-development/ozp-rest/commit/08936eeda054451ca808a0d2b889c76be950708a))
  * Approval status of search results is always being reported as IN_PROGRESS ([4c97a0de](https://github.com/ozone-development/ozp-rest/commit/4c97a0deab06e55df6155b099bdc126e765e1ad3))
  * Fixing search in reference to UriBuilder refactor ([d63f1f9e](https://github.com/ozone-development/ozp-rest/commit/d63f1f9ebf684d8a333e6607e7bf3a27d5429e4a))
  * the queryString param is not appearing in the self link for a search result ([389c4923](https://github.com/ozone-development/ozp-rest/commit/389c4923a22d2f22e1b05663bc2a107a1a2c03bf))
* **Search API:** filtering by category uses the wrong query param ([14466cc9](https://github.com/ozone-development/ozp-rest/commit/14466cc9fb0b521dcf9a83be23d5ab5ebbdf1063))
* **Tags:** Fixing Tag length validation ([c0c6b095](https://github.com/ozone-development/ozp-rest/commit/c0c6b095d57a4a11c78e143d26009da781d44856))
* **Sample Data:** add loadSampleData script for convenience ([8ca4b879](https://github.com/ozone-development/ozp-rest/commit/8ca4b87949e8439ead4aa113730caaf8e7d718de))


#### Features

* **Agency API:** Adding shortname to agency ([5e60ab72](https://github.com/ozone-development/ozp-rest/commit/5e60ab72d5acfa290dc76c60c03f481390a52540))
* **ChildObjectCollection:** Removing now-redundant classes StewardedOrganizations and ApplicationLibrary ([267d4be7](https://github.com/ozone-development/ozp-rest/commit/267d4be7a94d0a50081c58038a80890c7f58ec86))
* **Draft Listing:** OZF-416 OZF-417 Validation of non-draft-required fields ([e9d21030](https://github.com/ozone-development/ozp-rest/commit/e9d210306860ab4e19a7f7caba954d6a71145c94))
* **Draft Listings:** OZF-170 ([ba735749](https://github.com/ozone-development/ozp-rest/commit/ba735749aaebaff8496d22dfa9311d680435830e))
* **Error messages:** Improve Error messages being returned to client from Rest services ([7b9b1c34](https://github.com/ozone-development/ozp-rest/commit/7b9b1c34db3d290d0ffea31e2b4c7ffd77898c02))
* **HAL:**
  * Listing representations from search queries should be in HAL format ([f1b6e8db](https://github.com/ozone-development/ozp-rest/commit/f1b6e8dbac5872f9ef827014c49ae95bab4753dd))
  * Contact Type HAL ([58b318f3](https://github.com/ozone-development/ozp-rest/commit/58b318f3d32a70672c8d58cce7b7fb83e70be073))
  * Agency HAL reps ([a8854293](https://github.com/ozone-development/ozp-rest/commit/a88542936719eceeb2f3cdef7c78fdbb109c90d4))
  * Intents Collection HAL ([8d5f9720](https://github.com/ozone-development/ozp-rest/commit/8d5f9720edcf5524ed758fccf600094e997cc095))
  * Converting Type to HAL ([16fcc1d1](https://github.com/ozone-development/ozp-rest/commit/16fcc1d10ddb61287d3b867a4dbfe31898db793b))
  * fixing Category content-type ([eaf0eed4](https://github.com/ozone-development/ozp-rest/commit/eaf0eed4307be12816369306c52c4805ba3f1254))
  * Completing Category HAL transition ([38cdf7c9](https://github.com/ozone-development/ozp-rest/commit/38cdf7c9690eb61c3c727b579e852426ac0b03c2))
  * Category reps for HAL ([3960376d](https://github.com/ozone-development/ozp-rest/commit/3960376d58ef84ba4359eb1ecdcb5e28aa36649e))
  * RejectionListing HAL ([9c23f37e](https://github.com/ozone-development/ozp-rest/commit/9c23f37ef197e6e2370c2c161eeed0e63d8b6362))
* **Listing:** Adding singleton, height && width properties to Listing ([6cfb32f6](https://github.com/ozone-development/ozp-rest/commit/6cfb32f666c538bd730b71f11515be59566d6601))
* **Listing Activity:** Paging support for listing activity representation ([09e0649d](https://github.com/ozone-development/ozp-rest/commit/09e0649d1ae0be5004ed86e805b283f1692232df))
* **OZF-268 Approval:** Removing old RejectionJustification class ([ce8fce0b](https://github.com/ozone-development/ozp-rest/commit/ce8fce0b4807266c3e714a2abf00bb0d51ec084c))
* **Profile API:** sort by display name ([88e1b7a4](https://github.com/ozone-development/ozp-rest/commit/88e1b7a4b13df37308365632d53d9575614745dd))


#### Breaking Changes

* error response structure is changed

    To migrate the code follow the example below:

    Before:

    ```js
    {
        error: true,
        message: ‘’,
        resolvedMessages: []
    }
    ```

    After:

    ```js
    {
        message: ‘’,
        errors: {
            ‘fieldName’: ‘error message’,
            ‘fieldName2’: [‘error message’, ‘error message 2 ’] // if errors are more than 1
        }
    }
    ```

 ([7b9b1c34](https://github.com/ozone-development/ozp-rest/commit/7b9b1c34db3d290d0ffea31e2b4c7ffd77898c02))
* The following fields must have non-empty values for
    listings which are not IN_PROGRESS.  400 errors will otherwise
    result.

    width
    height
    whatIsNew
    descriptionShort
    isFeatured
    description
    versionName
    requirements
    agency
    launchUrl
    categories
    imageSmallUrl
    imageMediumUrl
    imageLargeUrl
    imageXlargeUrl
    contacts
    screenshots

    This is is addition to fields required for all Listings
    (title and type)

 ([ba735749](https://github.com/ozone-development/ozp-rest/commit/ba735749aaebaff8496d22dfa9311d680435830e))


