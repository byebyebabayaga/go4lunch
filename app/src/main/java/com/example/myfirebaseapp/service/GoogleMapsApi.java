package com.example.myfirebaseapp.service;

import com.example.myfirebaseapp.BuildConfig;
import com.example.myfirebaseapp.models.api.Autocomplete;
import com.example.myfirebaseapp.models.api.Details;
import com.example.myfirebaseapp.models.api.NearbySearch;
import com.example.myfirebaseapp.models.api.Place;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Request with retrofit
 */
public interface GoogleMapsApi {

    String GOOGLE_MAP_API_KEY = BuildConfig.GOOGLE_MAP_API_KEY ;

    // Nearby Search Request
    @GET("maps/api/place/nearbysearch/json?key="+GOOGLE_MAP_API_KEY)
    Observable<NearbySearch> getNearbyRestaurants(@Query("location") String location, @Query("radius") int radius, @Query("type") String type);

    // Place Details Request
    @GET("maps/api/place/details/json?key="+GOOGLE_MAP_API_KEY)
    Observable<Place> getRestaurantDetails(@Query("place_id") String placeId);

    // Place Autocomplete Request
    @GET("maps/api/place/autocomplete/json?strictbounds&key="+GOOGLE_MAP_API_KEY)
    Observable<Autocomplete> getAutocomplete(@Query("input") String input, @Query("radius") int radius, @Query("location") String location, @Query("type") String type);
    String queryPlaceNearbySearch = "maps/api/place/nearbysearch/json?key=" + GOOGLE_MAP_API_KEY;
    String queryPlaceDetails = "maps/api/place/details/json?" +
            "fields=place_id,name,vicinity,photos,rating,geometry,international_phone_number,website" +
            "&key=" + GOOGLE_MAP_API_KEY;
    String queryPlaceAutocompleteStrictbounds = "maps/api/place/autocomplete/json?strictbounds&key=" + GOOGLE_MAP_API_KEY;

    /**
     * A Retrofit GET request to the Google Places Nearby Search API.
     *
     * @param keyword  a term to be matched against all content that Google has indexed
     *                 for this place, including but not limited to name, type, and address, as well
     *                 as customer reviews and other third-party content.
     * @param type     restricts the results to places matching the specified type. Only one type may
     *                 be specified (if more than one type is provided, all types following the first
     *                 entry are ignored)
     * @param location the latitude/longitude around which to retrieve place information.
     *                 This must be specified as "latitude,longitude".
     * @param radius   defines the distance (in meters) within which to return place results.
     *                 The maximum allowed radius is 50 000 meters.
     * @return a {@link NearbySearch} object containing results
     * for nearby places returned by the API based on parameters.
     */
    @GET(queryPlaceNearbySearch)
    Call<NearbySearch> getNearbySearch(@Query("keyword") String keyword, @Query("type") String type, @Query("location") String location, @Query("radius") int radius);

    /**
     * A Retrofit GET request to the Google Places Details API.
     *
     * @param placeId a textual identifier that uniquely identifies a place. To retrieve information
     *                about the place, pass this identifier in the placeId field of a Places API request.
     * @return a {@link Details} object containing comprehensive details
     * of the place returned by the API based on the placeId provided.
     */
    @GET(queryPlaceDetails)
    Call<Details> getDetails(@Query("place_id") String placeId);

    /**
     * A Retrofit GET request to the Google Places Autocomplete API.
     *
     * @param input        the text string on which to search. The Place Autocomplete service will
     *                     return candidate matches based on this string and order results based
     *                     on their perceived relevance.
     * @param types        the types of place results to return.
     * @param location     the point around which you wish to retrieve place information.
     *                     Must be specified as "latitude,longitude".
     * @param radius       the distance (in meters) within which to return place results.
     * @param sessionToken a random string which identifies an autocomplete session for billing
     *                     purposes. If this parameter is omitted from an autocomplete request,
     *                     the request is billed independently.
     * @return an {@link Autocomplete} object containing predictions
     * for places around a location returned by the API based on parameters.
     * Strictbounds parameterized : Returns only those places that are strictly within the
     * region defined by location and radius. This is a restriction, rather than a bias, meaning
     * that results outside this region will not be returned even if they match the user input.
     */
    @GET(queryPlaceAutocompleteStrictbounds)
    Call<Autocomplete> getAutocomplete(@Query("input") String input, @Query("types") String types, @Query("location") String location, @Query("radius") int radius, @Query("sessiontoken") String sessionToken);
}



