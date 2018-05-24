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
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import io.sdchain.model.EffectCollection;
import io.sdchain.model.MemoCollection;
import io.sdchain.model.Transaction;
import io.sdchain.model.TransactionCollection;
/**
 * 
 * ClassName: TransactionCollectionDeserializer <br/>
 * the <em>TransactionCollection</em> utility of json deserializer
 * @see TransactionCollection
 * @author Sean
 */
public class TransactionCollectionDeserializer implements JsonDeserializer<TransactionCollection> {

    /**
     * @param json the json element
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    public TransactionCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(EffectCollection.class, new EffectCollectionDeserializer())
            .registerTypeAdapter(MemoCollection.class, new MemoCollectionDeserializer())
            .create();
        if (json.isJsonArray()) {
            Type transactionListType = new TypeToken<List<Transaction>>() {
            }.getType();
            List<Transaction> transaction = gson.fromJson(json, transactionListType);
            TransactionCollection collection = new TransactionCollection();
            collection.setData(transaction);
            return collection;
        }
        return gson.fromJson(json, typeOfT);
    }
}
