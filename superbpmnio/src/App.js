import React, { Component } from 'react';
//import logo from './logo.svg';
import './App.css';
import DockerImageList from "./components/DockerImageList";
import FileUploader from "./components/fileUploader"
import axios from "axios";
import BPMNDropzone from './BPMNDropzone';
import request from "superagent";


class App extends Component {

  onDrop = (files) => {
    // POST to a test endpoint for demo purposes
    
    const req = request.post('https://localhost:8080/files');

    files.forEach(file => {
      req.attach(file.name, file);
    });

    req.end();
    
  }

  state = {
    dockerImages: []
  };

  componentDidMount() {
    axios
      //.get("https://jsonplaceholder.typicode.com/users")
      .get("http://localhost:8080/dockerImages")
      .then(response => {
        console.log(response);

        // create an array of contacts only with relevant data
        const newDockerImages = response.data.map(c => {
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
        <BPMNDropzone/>
        <FileUploader/>
      </div>

    );
  }
}

export default App;
