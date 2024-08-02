import Axios from "axios";
export const baseUrl = "http://localhost:1337"

export const api = Axios.create({
    baseURL:`${baseUrl}/api`,
    timeout:1000
});