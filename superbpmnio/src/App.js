import React, { Component } from 'react';
//import logo from './logo.svg';
import './App.css';
import DockerImageList from "./components/DockerImageList";
import axios from "axios";

class App extends Component {

  state = {
    dockerImages: []
  };

  componentDidMount() {
    axios
      //.get("https://jsonplaceholder.typicode.com/users")
      .get("http://localhost:8080/api/dockerImages")
      .then(response => {

        // create an array of contacts only with relevant data
        const newDockerImages = response.data._embedded.dockerImages.map(c => {
          return {
            id: c.id,
            imageName: c.imageName,
            dockerfilePath: c.dockerfilePath,
            containerFile: c.containerFile,
            imageId: c.imageId
          };
        });

        // create a new "State" object without mutating 
        // the original State object. 
        const newState = Object.assign({}, this.state, {
          dockerImages: newDockerImages
        });

        // store the new state object in the component's state
        this.setState(newState);
      })
      .catch(error => console.log(error));
  }

  render() {
    return (
      <div className="App">
        <DockerImageList dockerImages = {this.state.dockerImages}/>
      </div>
    );
  }
}

export default App;
