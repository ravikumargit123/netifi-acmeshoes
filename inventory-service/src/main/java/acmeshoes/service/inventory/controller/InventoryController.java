/**
 * Copyright 2019 Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package acmeshoes.service.inventory.controller;

import acmeshoes.service.inventory.protobuf.InventoryService;
import acmeshoes.service.inventory.protobuf.ProductInventoryRequest;
import acmeshoes.service.inventory.protobuf.ProductInventoryResponse;
import acmeshoes.service.inventory.protobuf.SkuInventory;
import acmeshoes.service.inventory.protobuf.SkuInventoryRequest;
import acmeshoes.service.inventory.protobuf.SkuInventoryResponse;
import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Controller responsible for returning product and sku inventory information.
 */
@Component
public class InventoryController implements InventoryService {

    @Autowired
    private acmeshoes.service.inventory.service.InventoryService inv;

    @Override
    public Mono<ProductInventoryResponse> getProductInventory(ProductInventoryRequest message, ByteBuf metadata) {
        return inv.getProductInventory(message.getProductId(), message.getShowSoldOut())
                .collectList()
                .map(skuInvs -> {
                    ProductInventoryResponse.Builder piBuilder = ProductInventoryResponse.newBuilder()
                            .setProductId(message.getProductId());

                    skuInvs.forEach(si -> piBuilder.addSkus(SkuInventory.newBuilder()
                            .setSku(si.getSku())
                            .setUnits(si.getUnits())
                            .build()));

                    return piBuilder.build();
                });
    }

    @Override
    public Mono<SkuInventoryResponse> getSkuInventory(SkuInventoryRequest message, ByteBuf metadata) {
        return inv.getInventoryForSku(message.getSkuId())
                .map(skuInv -> SkuInventoryResponse.newBuilder()
                        .setSku(skuInv.getSku())
                        .setUnits(skuInv.getUnits())
                        .build());
    }
}
