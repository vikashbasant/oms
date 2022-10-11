# oms: Order Management Project

1: Add Product From the Database:

  POST- http://localhost:8070/osm/addProduct
  
  Request:
    {
        "name":"One Plus Smart TV",
        "availableQuantity":20,
        "price": 15000
    }
    
  Response:
  {
      "status": "Success",
      "message": "Product Add Successfully",
      "data": {
          "id": 4,
          "name": "One Plus Smart TV",
          "availableQuantity": 20,
          "price": 15000.0
      }
  }
  
    


2: Get All Project From Database:

  POST - http://localhost:8070/osm/gatAllProducts
  
  Response:
  {
      "status": "Success",
      "message": "Fetch All Product From DataBase",
      "data": [
          {
              "id": 2,
              "name": "mobile",
              "availableQuantity": 10,
              "price": 10000.0
          },
          {
              "id": 4,
              "name": "One Plus Smart TV",
              "availableQuantity": 20,
              "price": 15000.0
          },
          {
              "id": 1,
              "name": "laptop",
              "availableQuantity": 19,
              "price": 23456.4
          },
          {
              "id": 3,
              "name": "Smart Watch",
              "availableQuantity": 0,
              "price": 1500.0
          }
      ]
  }
  
3: Place Order
  POST- http://localhost:8070/osm/placeOrder
  
  Request:
  {
      "orderDescription": "Test Order orderDescription",
      "cartItems": [
          {
              "productId": 1,
              "quantity": 2
          },
          {
              "productId": 2,
              "quantity": 2
          }
      ],
      "customerEmail": "basantvikash360@gmail.com",
      "customerName": "Vikash Kumar Basant"
  }
  
  Response:
  {
      "status": "Success",
      "message": "Order Placed Successfully",
      "data": {
          "amount": 66912.8,
          "invoiceNumber": 1313,
          "date": "11-10-2022 14:31:25",
          "orderDescription": "Test Order orderDescription",
          "orderId": 2
      }
  }
  
  
4: Get the Information of Order Details

  POST- http://localhost:8070/osm/getOrderDetails
   
  Request:
  {
      "id" : 2
  }
  
  Response:
  
  {
      "status": "Success",
      "message": "Fetch Order Details Successfully",
      "data": {
          "id": 2,
          "orderDescription": "Test Order orderDescription",
          "customer": {
              "id": 1,
              "name": "Vikash Kumar Basant",
              "email": "basantvikash360@gmail.com"
          },
          "cartItems": [
              {
                  "id": 3,
                  "productId": 1,
                  "productName": "laptop",
                  "quantity": 2,
                  "amount": 46912.8
              },
              {
                  "id": 4,
                  "productId": 2,
                  "productName": "mobile",
                  "quantity": 2,
                  "amount": 20000.0
              }
          ]
      }
  }
  
