<?php

namespace BGR\Serrano\ProductoBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class CategoriaControllerTest extends WebTestCase
{
    public function testGetall()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/getAll');
    }

    public function testSave()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/save');
    }

}
