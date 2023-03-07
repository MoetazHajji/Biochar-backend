function initMap() {
    // Create a new map centered on a default location
    const map = new google.maps.Map(document.getElementById("map"), {
        center: {lat: 37.7749, lng: -122.4194},
        zoom: 10
    });

    // Add markers for each address with localizations
    const addresses = [
        {country: "United States", localizations: {en: "San Francisco", es: "San Francisco"}},
        {country: "Canada", localizations: {en: "Toronto", fr: "Toronto"}},
        {country: "Mexico", localizations: {en: "Mexico City", es: "Ciudad de México"}}
        // ...
    ];

    addresses.forEach(address => {
        // Geocode the address to get its coordinates
        const geocoder = new google.maps.Geocoder();
        geocoder.geocode({address: address.country}, function(results, status) {
            if (status === "OK") {
                const marker = new google.maps.Marker({
                    position: results[0].geometry.location,
                    map: map
                });

                // Create an info window with the localized information
                let content = `<h3>${address.localizations.en}</h3>`;
                for (let [key, value] of Object.entries(address.localizations)) {
                    content += `<p><b>${key}:</b> ${value}</p>`;
                }
                const infowindow = new google.maps.InfoWindow({
                    content: content
                });

                // Add an event listener to open the info window on click
                marker.addListener("click", () => {
                    infowindow.open(map, marker);
                });
            } else {
                console.error(`Geocode for ${address.country} failed with status: ${status}`);
            }
        });
    });
}