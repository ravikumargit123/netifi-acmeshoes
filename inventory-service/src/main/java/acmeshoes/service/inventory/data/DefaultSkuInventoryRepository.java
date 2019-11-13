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
package acmeshoes.service.inventory.data;

import acmeshoes.service.inventory.data.model.SkuInventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DefaultSkuInventoryRepository implements SkuInventoryRepository {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultSkuInventoryRepository.class);

    @Override
    public Flux<SkuInventory> findAll(String productId) {
        return null;
    }

    @Override
    public Mono<SkuInventory> findOne(String skuId) {
        return null;
    }
}
