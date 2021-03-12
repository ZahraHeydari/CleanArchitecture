# PhotoGallery

A simple Android app which displays a photo gallery besides a related detail page for each photo using `https://jsonplaceholder.typicode.com/` API.


## Scenario

This app:
- Displays a list of photos.
- When the user taps on each photo of the list, a detail page will be shown containing the original photo.


## Architecture

This app implemented using Clean Architecture containing 3 main layers:
1.  Presentation (used MVVM pattern for this layer)
2.  Data
3.  Domain


## Dependencies

- Retrofit
- Coroutines
- Moshi
- Room
- LiveData
- ViewModel
- Koin
- Coil (Image Loader)
- ViewBinding
- Mockk
- Leak canary (Memory leak identifier)


## Author
Zahra Heydari