/***
 * 
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer. 2. Redistributions in
 * binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution. 3. Neither the name of the
 * copyright holders nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written
 * permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.caelum.vraptor.validator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

public class ValidationsTest {

    public static class Client {
        private Long id;
        private String name;
        private int age;
        public int getAge() {
            return age;
        }
    }

    @Test
    public void canHandleTheSingleCheck() {
        Client guilherme = new Client();
        Validations validations = new Validations();
        validations.that(guilherme);
        validations.shouldBe(notNullValue());
    }



    @Test
    public void canHandleTheSingleCheckWhenProblematic() {
        Client guilherme = null;
        Validations validations = new Validations();
        validations.that(guilherme);
        validations.shouldBe(notNullValue());
        assertThat(validations.getErrors(), hasSize(1));
    }

    @Test
    public void canHandleInternalPrimitiveValidation() {
        Client guilherme = new Client();
        guilherme.age=22;
        Validations validations = new Validations();
        validations.that(guilherme).getAge();
        validations.shouldBe(greaterThan(17));
    }

    @Test
    public void canIgnoreInternalPrimitiveValidationIfAlreadyNull() {
        Client guilherme = null;
        Validations validations = new Validations();
        validations.that(guilherme).getAge();
        validations.shouldBe(greaterThan(17));
    }
    @Test
    public void complainsAboutInternalPrimitiveValidation() {
        Client guilherme = new Client();
        guilherme.age=15;
        Validations validations = new Validations();
        validations.that(guilherme).getAge();
        validations.shouldBe(greaterThan(17));
        assertThat(validations.getErrors(), hasSize(1));
    }

}