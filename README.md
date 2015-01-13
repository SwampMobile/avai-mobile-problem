#Avai Mobile Interview Problem

##The Problem:

Your task is to create an Android application that displays the search results from the DuckDuckGo search engine.  The app should allow user entry via a text field with an “Submit” button.  When pressing Submit, the app should query the DuckDuckGo API for results.  It should present a second Activity that displays the following:

 * “Definition" from the query results
 * A list of “text” from the “related topics” list 

While the above functionality is the only set of requirements, feel free to expand on this design and functionality as desired. 
 
The project should have a target API of 19 and a minimum API of 14. It should be able to be opened, compiled, and run in either Eclipse or Android Studio.  It should run on a connected Android device.
 
On completion, send us a zipped up project folder and an APK.
 
Required Resources:
DuckDuckGo Sample Results for Face Query:  http://api.duckduckgo.com/?q=face&format=json&pretty=1
DuckDuckGo API: https://duckduckgo.com/api

##Chosen Limitations:

I limited all Activitys to portrait mode.  In general I have found that rotation is only useful for photography, video playback, and games.

I did not use fragments anywhere due to the limited scope of this project.

Using the API exactly as demonstrated appeared to return different types of results in the “RelatedTopics” field.  Therefore, I added “disambig=1” to the query to restrict results to a uniform data format.

I thought about adding a “refresh” button or “pull-to-refresh” behavior to the search page, but opted not to spend the time on it.

##Solution Commanalities:

All following solutions utilize Retrofit to communicate with the Duck Duck Go API.  The API properties are defined in an annotated Interface that is read by Retrofit at runtime.  The Duck Duck Go responses are deserialized automatically by Retrofit/GSON based on annotated POJOs.

##Solution 1 - AsyncTask:

The first query option uses an AsyncTask to run the Duck Duck Go query on a worker thread and then publish the results on the main thread.  In general, one must take care with AsyncTasks to clean up all references between the Activity and the AsyncTask to avoid memory leaks as well as NPEs that can result from referencing dead Views.

##Solution 2 - RoboSpice:

The second query option uses Robospice.  Robospice handles all network traffic on a background thread through a Service and then publishes the results on the Main thread.  This app doesn’t demonstrate the power of this architecture, but in general the possibility of leaving an Activity and coming back is a real concern - this use-case is trivial when using Robospice and its SpiceManagers.

Robospice can also cache responses without any extra effort.  Caching was not used in this project, though it could have been by simply calling an alternate method.

##Solution 3 - RxJava:

The third query option uses RxJava.  In this case the RxJava Observable solution is very similar to an AsyncTask.  However, RxJava makes it very easy to compose sequential API calls and then combine/manipulate the results.  Also, with a little extra work, Observables can easily cache responses.

##Conclusion:

For the requirements as they stand, an AsyncTask would probably be fine.  Even if the search behavior was changed to real-time, an AsyncTask would make sense because the result of the query would apply only to the current UI.  However, in practice, most API communication has a deeper significance than immediate display, and sometimes multiple calls need to be composed.  In more sophisticated circumstances I would go with Robospice (for caching and navigation) or RxJava (for composing calls).