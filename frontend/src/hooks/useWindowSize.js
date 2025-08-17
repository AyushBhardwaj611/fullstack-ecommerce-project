// this is an example of the custom hook useWindowSize.js
import { useState, useEffect, use } from 'react';


function useWindowSize() {

    let [windowSize, setWindowSize] = useState([window.innerWidth, window.innerHeight]);
        
    function handleResize() {  
        setWindowSize([window.innerWidth, window.innerHeight]);
    }
    
    useEffect(() => {
        window.addEventListener('resize', handleResize);
    }, [])

    return windowSize;
    // This will return the current window size as an array [width, height]
    // You can use this hook in any component to get the current window size
}

export default useWindowSize;
// This hook can be used to get the current window size in a React component.

