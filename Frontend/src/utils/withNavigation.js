import { Component } from "react";
import { useNavigate } from "react-router-dom";
function withNavigation(Component) {
  return function Wrapper(props) {
    const navigate = useNavigate()
    return <Component navigate={navigate} {...props} />
  }
}
export default withNavigation;