package controller;

import Core.Restaurant;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import views.RestaurantListDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


public class RestaurantListController implements Initializable, MapComponentInitializedListener,
        DirectionsServiceCallback {

    public static Restaurant row;
    public double tempLat, tempLon, addressLat, addressLon, distance;
    private GoogleMap map;
    private GeocodingService geocodingService;
    private StringProperty address = new SimpleStringProperty();
    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();

    @FXML
    private TextField searchKey;
    @FXML
    private TextField toTextField;
    @FXML
    private Slider milesChoice;
    @FXML
    private TableView<Restaurant> restaurantTable;
    @FXML
    private TableColumn<Restaurant, String> nameColumn;
    @FXML
    private TableColumn<Restaurant, String> streetAddressColumn;
    @FXML
    private TableColumn<Restaurant, String> cityColumn;
    @FXML
    private TableColumn<Restaurant, String> stateColumn;
    @FXML
    private TableColumn<Restaurant, String> zipColumn;
    @FXML
    private TableColumn<Restaurant, String> latitudeColumn;
    @FXML
    private TableColumn<Restaurant, String> longitudeColumn;
    @FXML
    private TableColumn<Restaurant, String> phoneNumColumn;
    @FXML
    private TableColumn<Restaurant, String> photoColumn;
    @FXML
    GoogleMapView selectedMap;
    @FXML
    ImageView profilePic;
    @FXML
    public Button userLocation;

    // Reference to the main application.
    private RestaurantListDriver restaurantApp;

    /** Initializes the controller class. This method is automatically called after the fxml file has been loaded */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the person table with the two columns and a default map of the U.S.
        selectedMap.addMapInializedListener(this);
        address.bind(searchKey.textProperty());
        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(searchKey.textProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        streetAddressColumn.setCellValueFactory(cellData -> cellData.getValue().streetAddressProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        zipColumn.setCellValueFactory(cellData -> cellData.getValue().zipProperty());
        latitudeColumn.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());
        longitudeColumn.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());
        phoneNumColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNoProperty());
        photoColumn.setCellValueFactory(cellData -> cellData.getValue().photoProperty());

    }

    public void setRestaurantListApp(RestaurantListDriver restaurantApp) {
        this.restaurantApp = restaurantApp;

        // Add observable list data to the table
        restaurantTable.setItems(restaurantApp.getRestaurantData());
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(39.865895, -99.404319))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(4); //default zoom set to the U.S.
        map = selectedMap.createMap(mapOptions);
        DirectionsService directionsService = new DirectionsService();
        DirectionsPane directionsPane = selectedMap.getDirec();
    }

    /** Search logic and update table view for the result */
    @FXML
    private void toTextFieldAction(ActionEvent event) {
        DirectionsService directionsService = new DirectionsService();
        DirectionsPane directionsPane = selectedMap.getDirec();
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, selectedMap.getMap(), directionsPane));
    }

    @FXML
    public void filterByCoords() {
        String latitude = "";
        String longitude = "";
        String key = "";
        boolean coordinate = false;
        Restaurant restaurantKey;
        ObservableList<Restaurant> searchRestaurantResultTable2 = FXCollections.observableArrayList();

        key = searchKey.getText();

        //if key is empty
        if (key.equals("")) {
            restaurantTable.setItems(restaurantApp.getRestaurantData());
        }



        // Check if text entered is a coordinate by looking for ','
        if (key.indexOf(',') >= 0) {
            coordinate = true;
        }

        //if coordinate
        if (coordinate) {
            latitude = key.split(", ")[0]; // example 1: 38.340889,-90.399952
            longitude = key.split(", ")[1]; // example 2: 38.7517314, -77.4727505
            System.out.println(latitude + " " + longitude);
            restaurantKey = new Restaurant(latitude, longitude);
            if (restaurantApp.getRestaurantBSTree().contains(restaurantKey)) {
                //create the new observable list and add the result to this list
                searchRestaurantResultTable2.add(restaurantApp.getRestaurantBSTree().get(restaurantKey));
                //set new result of search to observrable list
                //update view
                restaurantTable.setItems(searchRestaurantResultTable2);
            } else {
                //reset the observable list
                searchRestaurantResultTable2.clear();
                //set new result of search to observrable list
                //update view
                restaurantTable.setItems(searchRestaurantResultTable2);
            }
        }
    }

    @FXML
    public void addressTextFieldAction(ActionEvent event) {
        ObservableList<Restaurant> searchRestaurantResultTable = FXCollections.observableArrayList();
        System.out.println("Miles selected = " + milesChoice.getValue());
        System.out.println(address.get());

        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                addressLat = results[0].getGeometry().getLocation().getLatitude();
                addressLon = results[0].getGeometry().getLocation().getLongitude();
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                addressLat = results[0].getGeometry().getLocation().getLatitude();
                addressLon = results[0].getGeometry().getLocation().getLongitude();
            }

            map.setCenter(latLong);

            for (int index = 0; index < restaurantTable.getItems().size(); index++) {
                Restaurant temp = restaurantTable.getItems().get(index);
                tempLat = Double.parseDouble(temp.getLatitude());
                tempLon = Double.parseDouble(temp.getLongitude());
                String tempAddress = (temp.getStreetAddress() + " " + temp.getCity() + " " + temp.getState() + " "
                        + temp.getZip().substring(0, 5));
                // Get distance for From and To
                distance = getDistance(addressLat, addressLon, tempLat, tempLon);
                System.out.println("Your Location Distance: " + addressLat + ", " + addressLon);
                System.out.println(tempAddress + ": " + tempLat + ", " + tempLon);
                System.out.println("Total Distance: " + distance);

                if (distance <= milesChoice.getValue()){
                    searchRestaurantResultTable.add(restaurantApp.getRestaurantBSTree().get(temp));

                    //Add a marker to the map
                    MarkerOptions markerOptions = new MarkerOptions();

                    markerOptions.position( new LatLong(tempLat, tempLon) )
                            .visible(Boolean.TRUE)
                            .animation(Animation.DROP)
                            .title(temp.getName() + "'s Location");

                    Marker marker = new Marker( markerOptions );

                    map.addMarker(marker);
                }
            }
            restaurantTable.setItems(searchRestaurantResultTable);
            // Reset the data
            addressLat = 0.0;
            addressLon = 0.0;
        });
        searchRestaurantResultTable.clear();
    }

    @FXML
    private void handleRowSelect(MouseEvent event) {
        if (event.getClickCount() == 2) {
            row = restaurantTable.getSelectionModel().getSelectedItem();
            if (row == null) return;
            if (row != null) {
                System.out.println(row.toString());

                MapOptions mapOptions = new MapOptions();

                double lat = Double.parseDouble(row.getLatitude());
                double lon = Double.parseDouble(row.getLongitude());

                mapOptions.center(new LatLong(lat, lon))
                        .mapType(MapTypeIdEnum.ROADMAP)
                        .overviewMapControl(false)
                        .panControl(false)
                        .rotateControl(false)
                        .scaleControl(false)
                        .streetViewControl(false)
                        .zoomControl(false)
                        .zoom(12);

                map = selectedMap.createMap(mapOptions);

                //Add a marker to the map
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position( new LatLong(lat, lon) )
                        .visible(Boolean.TRUE)
                        .title(row.getName() + "'s Location");

                Marker marker = new Marker( markerOptions );

                map.addMarker(marker);
                String rowAddress = (row.getStreetAddress() + " " + row.getCity() + " " + row.getState() + " "
                        + row.getZip().substring(0, 5));
                toTextField.setText(rowAddress);

                if (from.get() != ""){
                    DirectionsService directionsService = new DirectionsService();
                    DirectionsPane directionsPane = selectedMap.getDirec();
                    DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
                    directionsService.getRoute(request, this, new DirectionsRenderer(true, selectedMap.getMap(), directionsPane));
                }

            }
        }
    }

    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @Override
    public void directionsReceived(DirectionsResult directionsResult, DirectionStatus directionStatus) {

    }
}
