/*
 * Copyright SDChain Alliance
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.sdchain.net.json;

import java.lang.reflect.Type;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import io.sdchain.model.BalanceCollection;
import io.sdchain.model.EffectCollection;
import io.sdchain.model.MemoCollection;
import io.sdchain.model.OrderCollection;
import io.sdchain.model.PaymentCollection;
import io.sdchain.model.TransactionCollection;
import io.sdchain.model.AccountWallet;
/**
 * 
 * ClassName: WalletDeserializer <br/>
 * the <em>Wallet</em> utility of json deserializer
 * @see AccountWallet
 * @author Sean
 */
public class WalletDeserializer implements JsonDeserializer<AccountWallet> {

    /**
     * @param json the json element
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    public AccountWallet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(BalanceCollection.class, new BalanceCollectionDeserializer())
            .registerTypeAdapter(PaymentCollection.class, new PaymentCollectionDeserializer())
            .registerTypeAdapter(OrderCollection.class, new OrderCollectionDeserializer())
            .registerTypeAdapter(TransactionCollection.class, new TransactionCollectionDeserializer())
            .registerTypeAdapter(EffectCollection.class, new EffectCollectionDeserializer())
            .registerTypeAdapter(MemoCollection.class, new MemoCollectionDeserializer())
            .create();
        AccountWallet wallet = gson.fromJson(json, AccountWallet.class);
        return wallet;
    }
}
