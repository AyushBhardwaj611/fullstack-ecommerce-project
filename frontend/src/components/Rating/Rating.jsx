import Icon from "@mdi/react";
import { mdiStar, mdiStarOutline } from "@mdi/js";

function Rating ({ rating, maxRating , size}) {
    return (
       <div style={{ fontSize: "20px", color: "gold" }}>
        ★★★★☆
        </div>
    );
}

export default Rating;

// NOTE --> we are not getting the rating from the backend right now
// so we are just displaying the rating as a static text
// in the future we can get the rating from the backend and display it