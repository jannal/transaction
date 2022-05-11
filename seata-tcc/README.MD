[TOC]

# 测试

1. 调用聚合接口

   ```bash
   $ curl -H "Content-Type: application/json" -X POST \
   -d '{"amount": 1000,"accountFromId": "jannal","accountToId": "tom","transferSerialNumber":"87316345-04f4-4102-8fac-78a97f1f7d7b"}'  \
   http://127.0.0.1:9000/api/v1/transfer
   ```

   