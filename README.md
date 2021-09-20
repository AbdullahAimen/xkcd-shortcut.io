# [Model–View–Presenter (MVP)](https://github.com/AbdullahAimen/xkcd-shortcut.io)
  
[![kotlin](https://img.shields.io/badge/Kotlin-1.3.xxx-brightgreen.svg)](https://kotlinlang.org/) [![Mockk](https://img.shields.io/badge/Mockk-testing-yellow.svg)](https://mockk.io/) [![Espresso](https://img.shields.io/badge/Espresso-testing-lightgrey.svg)](https://developer.android.com/training/testing/espresso/)

[![Junit4](https://img.shields.io/badge/Testing-JUnit4-green)](https://developer.android.com/training/testing/junit-rules)
[![Hilt](https://img.shields.io/badge/Hilt-di-brightgreen)](https://dagger.dev/hilt/)  [![Kotlin-Android-Extensions ](https://img.shields.io/badge/Kotlin--Android--Extensions-plugin-red.svg)](https://kotlinlang.org/docs/tutorials/android-plugin.html) [![MVVM ](https://img.shields.io/badge/Clean--Code-MVVM-brightgreen.svg)](https://github.com/googlesamples/android-architecture)  ![MVP ](https://img.shields.io/badge/Clean--Code-MVP-brightgreen.svg)  
  
  
![android-mvp-flow](https://miro.medium.com/max/778/0*8ps4RHy13puZY4dK.png)  
  
Model–view–presenter (MVP) is a derivation of the model–view–controller (MVC) architectural pattern which mostly used for building user interfaces. In MVP, the presenter assumes the functionality of the “middle-man”. In MVP, all presentation logic is pushed to the presenter.
Check here for [MVVM](https://github.com/AbdullahAimen/mvvm-koin-appBase)   

**Hilt for DI**
-----------------------------

![8399](https://miro.medium.com/max/2000/1*9YvVuOFK165EEN7ebv1LEQ.png)
- The goals of Hilt are:

    - To simplify Dagger-related infrastructure for Android apps.
    - To create a standard set of components and scopes to ease setup, readability/understanding, and code sharing           between apps.
    - To provide an easy way to provision different bindings to various build types (e.g. testing, debug, or release).

- Why use Hilt?
  - Reduced boilerplate
  - Decoupled build dependencies
  - Simplified configuration
  - Improved testing
  - Standardized components

- Add hilt dependency to your gradle file 
>     // Hilt
>     implementation "com.google.dagger:hilt-android:2.38.1"
>     kapt "com.google.dagger:hilt-compiler:2.38.1"
  
**Handle Retrofit with Callbacks**  
-----------------------------  
![8399](https://user-images.githubusercontent.com/1812129/68318999-e93b0680-00bd-11ea-9d76-058222c7a654.png) 
>     // Add Retrofit2  
>     implementation 'com.squareup.retrofit2:retrofit:2.6.2'  
>     implementation 'com.squareup.retrofit2:converter-gson:2.6.2'  
>     implementation 'com.squareup.okhttp3:okhttp:4.2.2'  
  
  
- Make Retrofit Calls.  
  
>     @GET
>     fun loadComic(@Url url: String): Call<Comic>  
  
  
**Keep your code clean according to MVP**  
-----------------------------  
![8399](https://miro.medium.com/max/1400/1*QRAB2NLKN6HgJf_RQo1gpw.png)
 - Our business logic is completely decoupled from our UI. It makes our code very easy to maintain and test.  
 - add `UseCaseHandler` handles execution of a `UseCase`. We should never block the UI when we fetch data from the database or our remote server.
 - The purpose of the `UseCase`s is to be a mediator between your Presenters and Repositorys.
 
----------  
  
**The purpose of the app**  
-----------------------------  
 - The task is to fetch the data from external server [xkcd Comic provider](https://xkcd.com/json.html) in case of network availability and save data over Room db to be used        later if there is no internet.
 - User can navigate through multiple comics and can skip to last or first through buttons 
 - Comics like:
  - ![8399](https://imgs.xkcd.com/comics/just_alerting_you.jpg)
  - ![8399](https://imgs.xkcd.com/comics/pi.jpg)
  - ![8399](https://imgs.xkcd.com/comics/family_circus.jpg)
  
  
  
 
----------  
  
[![Email](https://img.shields.io/badge/ContactMe!-eng.abdullah.aimen%40gmail.com-orange)](mailto:eng.abdullah.aimen#gmail.com)
  
----------  
**LICENSE**  
-------------------  
  
  
Copyright [2021] [Abdullah Aimen] 
  
Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at  
  
 http://www.apache.org/licenses/LICENSE-2.0  
Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.
