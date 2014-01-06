<?php

namespace BGR\Serrano\ProductoBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class FraccionableControllerTest extends WebTestCase
{
    public function testGetfraccionablebyid()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/getFraccionableById');
    }

}
