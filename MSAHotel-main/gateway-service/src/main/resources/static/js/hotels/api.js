export const hotelApi = axios.get("http://localhost:3000/hotel", {
  params: {
    test: "검색어",
  },
});
